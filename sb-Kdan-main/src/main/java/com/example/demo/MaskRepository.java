package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface MaskRepository extends JpaRepository<Mask, Integer> {
	List<Mask> findByName(String name);

	@Query(value = " SELECT mask.* FROM mask "
			+ " where mask.name like %?1%  "
			+ " order by mask.pharmacy_id , mask.id"
			, nativeQuery = true)
	List<Mask> findByNameLike(String name);
	

	// 使用sql查詢
	@Query(value = " SELECT * FROM mask "
			+ " join pharmacy on mask.pharmacy_id = pharmacy.id "
			+ " where pharmacy.name = ?1  "
			+ " order by mask.name"
			, nativeQuery = true)
	//?1表示第一個引數，?2表示第二個引數
	List<Mask> findMaskByPharmacyName(String name);


	// 使用sql查詢
	@Query(value = " SELECT mask.* FROM mask "
			+ " join pharmacy on mask.pharmacy_id = pharmacy.id "
			+ " where mask.name = ?1  "
			+ " and mask.pharmacy_id =?2"
			+ " order by mask.name"
			, nativeQuery = true)
	//?1表示第一個引數，?2表示第二個引數
	List<Mask> findMaskByMaskNameAndPharmacyId(String name,int pharmacy_id);

	
}