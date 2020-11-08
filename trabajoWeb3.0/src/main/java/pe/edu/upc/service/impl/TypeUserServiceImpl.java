package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.TypeUser;
import pe.edu.upc.repository.TypeUserRepository;
import pe.edu.upc.service.ITypeUserService;

@Service
public class TypeUserServiceImpl implements ITypeUserService{

	@Autowired
	private TypeUserRepository tyusR;
	
	@Override
	@Transactional
	public Integer insert(TypeUser tu) {
		int rpta=0;
		if(rpta == 0) {
			tyusR.save(tu);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(int idTypeUser) {
		tyusR.deleteById(idTypeUser);
	}

	@Override
	@Transactional
	public List<TypeUser> list() {
		return tyusR.findAll();
	}

}
