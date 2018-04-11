package com.zdjf.service.source;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.source.ISourceDao;
import com.zdjf.domain.source.Source;
import com.zdjf.domain.source.SourceVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class SourceService implements ISourceService {

	private ISourceDao sourceDao;
	@Override
	public Serializable save(Source source) {
		return sourceDao.save(source);
	}

	@Override
	public void delete(Source source) {
		sourceDao.delete(source);
	}

	@Override
	public void update(Source source) {
		sourceDao.update(source);
	}

	@Override
	public Source selectForObjectById(Long id) {
		Source source=new Source();
		source.setId(id);
		return (Source) sourceDao.selectForObject("selectSourceById", source);
	}

	@Override
	public void updateSourceById(Source source) {
		sourceDao.update("updateSourceById", source);
	}

	@Override
	public void deleteById(Long id) {
		sourceDao.delete("deleteSourceById", id);
	}

	@Override
	public Page page(Page page, Source source) {
		return sourceDao.page(page, source);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Source source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Source source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Source source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Source source) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<SourceVo> selectForList(Source source) {
		return (List<SourceVo>) sourceDao.selectForList("selectSource", source);
	}
	@Autowired
	public void setSourceDao(
			@Qualifier("sourceDao")ISourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}

}
