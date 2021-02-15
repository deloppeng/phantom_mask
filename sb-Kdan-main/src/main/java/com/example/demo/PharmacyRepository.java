package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
	List<Pharmacy> findByName(String name);

	@Query(value = " SELECT pharmacy.* FROM pharmacy "
			+ " where pharmacy.name like %?1%  "
			+ " order by pharmacy.id "
			, nativeQuery = true)
	List<Pharmacy> findByNameLike(String name);

	// 使用sql查詢
	@Query(value = " SELECT pharmacy.* FROM pharmacy "
			+ " join opentime on opentime.pharmacy_id = pharmacy.id "
			+ " where opentime.openweek = ?1  "
			+ " and parsedatetime(opentime.opentime,'hh:mm','en','GMT') <= parsedatetime(?2,'hh:mm','en','GMT') "
			+ " and parsedatetime(opentime.closetime,'hh:mm','en','GMT') >= parsedatetime(?2,'hh:mm','en','GMT') "
			+ " order by pharmacy.id"
			, nativeQuery = true)
	//?1表示第一個引數，?2表示第二個引數
	List<Pharmacy> findPharmacyByOpenweek(String openweek,String time);
	
	// 使用sql查詢
	@Query(value = " SELECT distinct pharmacy.* FROM pharmacy "
			+ " join opentime on opentime.pharmacy_id = pharmacy.id "
			+ " where parsedatetime(opentime.opentime,'hh:mm','en','GMT') <= parsedatetime(?1,'hh:mm','en','GMT') "
			+ " and parsedatetime(opentime.closetime,'hh:mm','en','GMT') >= parsedatetime(?1,'hh:mm','en','GMT') "
			+ " order by pharmacy.id"
, nativeQuery = true)
	//?1表示第一個引數，?2表示第二個引數
	List<Pharmacy> findPharmacyByOpentime(String time);
	

	// 使用sql查詢
	@Query(value = " SELECT distinct pharmacy.* FROM pharmacy "
			+ " join mask on mask.pharmacy_id = pharmacy.id "
			+ " where mask.price <=  ?1"
			+ " order by pharmacy.id "
, nativeQuery = true)
	//?1表示第一個引數，?2表示第二個引數
	List<Pharmacy> findPharmacyByMaskPrice(BigDecimal price);
}