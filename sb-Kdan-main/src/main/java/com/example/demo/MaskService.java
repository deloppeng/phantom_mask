package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
 
@Service
public class MaskService {
     
    @Autowired
    MaskRepository repository;
  
    @PersistenceContext//@[email protected]
    EntityManager em;
    public List<Mask> getAll()
    {
        List<Mask> MaskList = repository.findAll();
         
        if(MaskList.size() > 0) {
            return MaskList;
        } else {
            return new ArrayList<Mask>();
        }
    }

    public Mask getMaskById(Integer id)  
    {
        Optional<Mask> MaskObj = repository.findById(id);
        return MaskObj.get();
    }

    public List<Mask> getMaskByName(String name)  
    {
    	return repository.findByName(name);
    }

    public List<Mask> findMaskByPharmacyName(String name)  
    {
    	return repository.findMaskByPharmacyName(name);
    }
   
     

    public Mask updateMask(Mask entity, Integer id)  
    {
        Optional<Mask> mask = repository.findById(id);
        try
        {
        	Mask newEntity = mask.get();

        	if(entity.getName()!=null) {
            	newEntity.setName(entity.getName());
            }
        	if(entity.getPrice()!=null) {
            	newEntity.setPrice(entity.getPrice());
            }
            

            newEntity = repository.save(newEntity);
             
            return newEntity;
        }
        catch(Exception e)
        {
        	return null;
        }
    } 
    

    public String deleteMaskByMaskNameAndPharmacyId(String name ,Integer id)  
    {
    	List<Mask> mask = repository.findMaskByMaskNameAndPharmacyId(name,id);
        if(mask.size() == 0)
        {
        	return "Deleted Fail , No Mask name = " + name;
        }
        repository.deleteById(mask.get(0).getId());
        
        return "Deleted MaskID " + id;
    } 
    
    
   
}