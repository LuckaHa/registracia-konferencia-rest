package upjs.sk.upjs.registracia_itat_rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.registracia_itat.entity.Workshop;
import sk.upjs.registracia_itat.persitent.DaoFactory;
import sk.upjs.registracia_itat.persitent.WorkshopDao;

@CrossOrigin
@RestController
public class WorkshopRestController {
	
	WorkshopDao wsDao = DaoFactory.INSTANCE.getWorkshopDao();
	
	@RequestMapping("/workshops")
	public List<Workshop> getAll() {
		return wsDao.getAll();
	}

}
