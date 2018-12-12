package upjs.sk.upjs.registracia_itat_rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.registracia_itat.entity.Participant;
import sk.upjs.registracia_itat.persitent.DaoFactory;
import sk.upjs.registracia_itat.persitent.ParticipantDao;
import sk.upjs.registracia_itat.persitent.ParticipantNotFoundException;

/*
 * spristupnuje participanta cez REST API
 * k participantovi sa dostaneme cez pom v projekte registracia-itat
 * do rest pom-ka pridame do dependency <groupId>, <artifactId>, <version> + <scope>compile</scope>
 * 
 * heslo v DAOFactory musi byt rovnake ako v SQL databaze
 * na nasej dat. Drop Schema...
 * 
 * pridat do chromu / stiahnut Postman - GET http://localhost:8080/participants
 */
@CrossOrigin
@RestController
public class ParticipantRestController {
	
	private ParticipantDao participantDAO = DaoFactory.INSTANCE.getParticipantDao();
	
	@RequestMapping("/participants")
	public List<Participant> getParticipants() {
		return participantDAO.getAll();
	}
	
	@RequestMapping(value="/participants", method = RequestMethod.POST)
	public Participant addParticipant(@RequestBody Participant participant) throws DaoException {
		try {
			participantDAO.add(participant);
			return participant;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/participants", method = RequestMethod.PUT)
	public void saveParticipant(@RequestBody Participant participant) {
		participantDAO.save(participant);
	}
	
	@RequestMapping(value="/participants/{id}", method = RequestMethod.DELETE)
	public void deleteParticipant(@PathVariable long id) throws ParticipantNotFoundException { //id vytiahnem zo zadanej cesty
		participantDAO.delete(id);
	}
}
