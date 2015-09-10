package hello;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private final AtomicLong counterId = new AtomicLong();

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatForm(Model model) {
		// refresh list //
		List<Chat> listchats = selectAllChats();
		model.addAttribute("listchats", listchats);
		log.info("DATA LENGTH : " + listchats.size());
		model.addAttribute("chat", new Chat());
		return "chat";
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public String chatSubmit(@ModelAttribute Chat chat, Model model) {
		// add to sql database //
		jdbcTemplate.update("INSERT INTO chats(user, message) VALUES (?,?)", chat.getUser(), chat.getMessage());
		// refresh list //
		List<Chat> listchats = selectAllChats();
		model.addAttribute("listchats", listchats);
		log.info("DATA LENGTH : " + listchats.size());
		return "chat";
	}

	public List<Chat> selectAllChats() {
		List<Chat> listchats = jdbcTemplate.query("SELECT id, user, message FROM chats",
				(rs, rowNum) -> new Chat(rs.getLong("id"), rs.getString("user"), rs.getString("message")));
		Collections.reverse(listchats);
		return listchats;
	}
}
