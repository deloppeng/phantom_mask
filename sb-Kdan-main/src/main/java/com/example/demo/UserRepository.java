package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByName(String name);


	@Query(value = " SELECT top ?1 User.id, User.name ,SUM(transaction_Amount) AmountTotal FROM User "
			+ " join purchase_Histories on purchase_Histories.user_id = User.id "
			+ " where parsedatetime(purchase_Histories.transaction_Date,'dd-MM-yyyy','en','GMT') >= parsedatetime(?2,'dd-MM-yyyy','en','GMT') "
			+ " and parsedatetime(purchase_Histories.transaction_Date,'dd-MM-yyyy','en','GMT') <= parsedatetime(?3,'dd-MM-yyyy','en','GMT') "
			+ " GROUP BY User.name "
			+ " order by AmountTotal DESC "
			, nativeQuery = true)
	List<Object[]> findTopN(int topN ,String SDate,String EDate);

	@Query(value = " SELECT  COUNT(mask_id) CountAmount ,SUM(transaction_Amount) AmountTotal FROM purchase_Histories "
			+ " where parsedatetime(transaction_Date,'dd-MM-yyyy','en','GMT') >= parsedatetime(?1,'dd-MM-yyyy','en','GMT') "
			+ " and parsedatetime(transaction_Date,'dd-MM-yyyy','en','GMT') <= parsedatetime(?2,'dd-MM-yyyy','en','GMT') "
			, nativeQuery = true)
	List<Object[]> findTotalAmount(String SDate,String EDate);
	
	
	
}