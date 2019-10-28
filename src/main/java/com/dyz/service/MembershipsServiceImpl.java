package com.dyz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;import org.omg.IOP.CodeSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dyz.dao.MembershipsRepository;
import com.dyz.entity.Memberships;
import com.dyz.entity.Search;
import com.dyz.entity.Statistics;

@Service
public class MembershipsServiceImpl implements MembershipsService {

	@Autowired
	private MembershipsRepository repository;
	@Override
	public List<Memberships> queryByMemberPage(Search search, Pageable pageable) {
		Sort sort=new Sort(Sort.Direction.ASC,"MembershipId");
		Pageable pagea=new PageRequest(0, 1);
		Page<Memberships> findAll = repository.findAll(this.getWhereClause(search),pageable);
		return findAll.getContent();
	}
	private Specification<Memberships> getWhereClause(Search search) {
		// TODO Auto-generated method stub
		return new Specification<Memberships>() {
			public Predicate toPredicate(Root<Memberships> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
			List<Expression<Boolean>> exList=predicate.getExpressions();
				
				if(search.getDepartment()!=null && !"".equals(search.getDepartment())) {
					exList.add(cb.like(root.<String>get("department"),"%"+search.getDepartment()+"%"));
				}
				
				if(search.getSpecialty()!=null && !"".equals(search.getSpecialty())) {
					 exList.add(cb.equal(root.get("specialty").as(String.class),search.getSpecialty()));			
				}
				return predicate;
			}
		
		};
	}
	/**
	 * eCharts饼图
	 */
	@Override
	public List<Object> selectdeptmentAndDegreeCount() {
		// TODO Auto-generated method stub
		//院系、专业num
		//院系、专业、人num
		List<Map<String,Object>> deptmentNum=repository.selectCountdegree();
		List<Map<String,Object>> stuNum=repository.selectCountdeptment();
		
		List<Object> list=new ArrayList<>();
		list.add(deptmentNum);
		list.add(stuNum);
		
		/*List<Map<String,Object>> newStu = new ArrayList<>();
		for(Map<String,Object> map1 : stuNum) {
			Map<String,Object> map = new LinkedHashMap<>(map1);
			deptmentNum.forEach((key)->{
				System.out.println(map1.get("degree") +"===="+key.get("departmentNum"));
					if(key.get("degree").equals(map1.get("degree"))) {
						System.out.println(map1.get("degree") +"===="+key.get("departmentNum"));
						map.put("departmentNum", key.get("departmentNum"));
						newStu.add(map);
					}
			});
		}*/
		return list;
	}
}
