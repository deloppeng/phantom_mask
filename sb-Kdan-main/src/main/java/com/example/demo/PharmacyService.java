package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PharmacyService {
     
    @Autowired
    PharmacyRepository repository;
    @Autowired
    MaskRepository maskRepository;
  
    @PersistenceContext//@[email protected]
    EntityManager em;
    public List<Pharmacy> getAll()
    {
        List<Pharmacy> PharmacyList = repository.findAll();
         
        if(PharmacyList.size() > 0) {
            return PharmacyList;
        } else {
            return new ArrayList<Pharmacy>();
        }
    }

    public Pharmacy getPharmacyById(Integer id)  
    {
        Optional<Pharmacy> PharmacyObj = repository.findById(id);
        return PharmacyObj.get();
    }

    public List<Pharmacy> getPharmacyByName(String name)  
    {
    	return repository.findByName(name);
    }
    public List<Pharmacy> findPharmacyByOpenweek(String openweek,String opentime)  
    {
    	return repository.findPharmacyByOpenweek(openweek,opentime);
    }
    public List<Pharmacy> findPharmacyByOpentime(String opentime)  
    {
    	return repository.findPharmacyByOpentime(opentime);
    }
    public List<Pharmacy> findPharmacyByMaskPrice(BigDecimal price)  
    {
    	return repository.findPharmacyByMaskPrice(price);
    }
  

    public List<PharmacyMask> findPharmacyandMaskByKeyWord(String keyWord)  
    {
    	List<Pharmacy> PharmacyList = repository.findByNameLike(keyWord);
    	List<Mask> MaskList = maskRepository.findByNameLike(keyWord);
    	List<PharmacyMask>  PharmacyMaskList = new ArrayList<PharmacyMask>();
    	for(int i=PharmacyList.size()-1;i>=0;i--)
    	{
    		int hit =0;
    		for(int j=MaskList.size()-1;j>=0;j--)
        	{
    			if(MaskList.get(j).getPharmacy_id() == PharmacyList.get(i).getId())
    			{
    	    		PharmacyMask temp = new PharmacyMask();
    	    		temp.setMaskName(MaskList.get(j).getName());
    	    		temp.setMaskPrice(MaskList.get(j).getPrice());
    	    		temp.setPharmacyName(PharmacyList.get(i).getName());
    	    		PharmacyMaskList.add(temp);
    	    		MaskList.remove(j);
    	    		hit =1;
    			}
        	}
    		////代表該藥局已經加入清單，在此移除避免後面重複處理
    		if(hit==1)
    		{
    			PharmacyList.remove(i);
    		}
    	}
    	for(int i=PharmacyList.size()-1;i>=0;i--)
    	{
    		PharmacyMask temp = new PharmacyMask();
    		temp.setPharmacyName(PharmacyList.get(i).getName());
    		PharmacyMaskList.add(temp);
    	}
    	for(int i=MaskList.size()-1;i>=0;i--)
    	{
    		PharmacyMask temp = new PharmacyMask();
    		temp.setMaskName(MaskList.get(i).getName());
    		temp.setMaskPrice(MaskList.get(i).getPrice());
    		PharmacyMaskList.add(temp);
    	}
    	return PharmacyMaskList;
    }
    

    public Pharmacy updatePharmacy(Pharmacy entity, Integer id)  
    {
        Optional<Pharmacy> pharmacy = repository.findById(id);
        try
        {
        	Pharmacy newEntity = pharmacy.get();

        	if(entity.getName()!=null) {
            	newEntity.setName(entity.getName());
            }
            

            newEntity = repository.save(newEntity);
             
            return newEntity;
        }
        catch(Exception e)
        {
        	return null;
        }
    } 
    
   
}