package com.example.demo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
 
@Service
public class UserService {
     
    @Autowired
    UserRepository repository;

    @Autowired
    PurchaseHistoriesRepository PurchaseHistoriesRepository;
    @Autowired
    PharmacyRepository PharmacyRepository;
  

    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    
    @PersistenceContext//@[email protected]
    EntityManager em;
    public List<User> getAll()
    {
        List<User> UserList = repository.findAll();
         
        if(UserList.size() > 0) {
            return UserList;
        } else {
            return new ArrayList<User>();
        }
    }

    public User getUserById(Integer id)  
    {
        Optional<User> UserObj = repository.findById(id);
        return UserObj.get();
    }

    public List<User> getUserByName(String name)  
    {
    	return repository.findByName(name);
    }
    @Test
    public List<UserTotalAmount> findTopN(int topN ,String SDate,String EDate)  
    {
    	List<Object[]> object = repository.findTopN(topN,SDate,EDate);
    	List<UserTotalAmount> data = new ArrayList<UserTotalAmount> ();

		for (int i = 0; i < object.size(); i++)
		{
			Object[] temp = object.get(i);
			UserTotalAmount temp1 = new UserTotalAmount();
			temp1.setName(temp[1].toString());
			temp1.setAmountTotal(new BigDecimal(temp[2].toString()) );
			data.add(temp1);
		}
    	//List<Object> = repository.findTopN(topN,SDate,EDate);
    	return data;
    }


    public TotalAmount findTotalAmount(String SDate,String EDate)  
    {
    	List<Object[]> object = repository.findTotalAmount(SDate,EDate);
    	if(object.size()>0)
    	{
    		TotalAmount data =new TotalAmount();
    		for (int i = 0; i < object.size(); i++)
    		{
    			Object[] temp = object.get(i);
    			data.setAmountTotal(new BigDecimal(temp[1].toString()));
    			data.setCountAmount(temp[0].toString());
    			return data;
    			
    		}
    	}
    	return null;
    	
    }
    

    public int createPurchaseHistories(int user_id ,int pharmacyID, int MaskId, BigDecimal transactionAmount, String transactionDate) throws SQLException  
    {
    	PurchaseHistories entity = new PurchaseHistories();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        
    	Date date;
		try {
			date = sdf.parse(transactionDate);
	    	entity.setMask_id(MaskId);
	    	entity.setPharmacy_id(pharmacyID);
	    	entity.setUser_id(user_id);
	    	entity.setTransactionDate(date);
	    	entity.setTransactionAmount(transactionAmount);
	    	PurchaseHistoriesRepository.save(entity);
	    	
	    	////更新用戶帳戶
	    	Optional<User> UserObj = repository.findById(user_id);
	    	User newEntity = UserObj.get();
	    	BigDecimal BigDecimalTemp = newEntity.getCash_balance();
	    	BigDecimalTemp = BigDecimalTemp.subtract(transactionAmount);
	    	newEntity.setCash_balance(BigDecimalTemp);
            newEntity = repository.save(newEntity);
            
            ////更新藥局帳戶//
	    	Optional<Pharmacy> PharmacyObj = PharmacyRepository.findById(pharmacyID);
	    	Pharmacy newPharmacyEntity = PharmacyObj.get();
	    	BigDecimalTemp = newPharmacyEntity.getCash_balance();
	    	BigDecimalTemp = BigDecimalTemp.add(transactionAmount);
	    	newPharmacyEntity.setCash_balance(BigDecimalTemp);
	    	newPharmacyEntity = PharmacyRepository.save(newPharmacyEntity);

            platformTransactionManager.commit(transactionStatus); // 提交
		} catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus); // 回滾
			return 0;
		}
    	return 1;
    	
    }
    
    
   
}