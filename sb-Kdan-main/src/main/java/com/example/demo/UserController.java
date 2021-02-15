package com.example.demo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService service;
 
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> list = service.getAll();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User>  getUserById(@PathVariable("id") Integer id) 
    {
        User entity = service.getUserById(id);
        return new ResponseEntity<User>(entity, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<User>> findByName(@PathVariable("name") String name) {
        List<User> list = service.getUserByName(name);
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("/findTopN")
    public ResponseEntity<List<UserTotalAmount>> findTopN( int topN ,String SDate,String EDate) {
        List<UserTotalAmount> list = service.findTopN(topN,SDate,EDate);
        return new ResponseEntity<List<UserTotalAmount>>(list, HttpStatus.OK);
    }


    @GetMapping("/findTotalAmount")
    public ResponseEntity<TotalAmount> findTotalAmount( String SDate,String EDate) {
        TotalAmount list = service.findTotalAmount(SDate,EDate);
        return new ResponseEntity<TotalAmount>(list, HttpStatus.OK);
    }

    //新增客戶購買資料
    @PostMapping
    public ResponseEntity<List<String>> createPurchaseHistories(int user_id ,int pharmacyID, int MaskId, BigDecimal transactionAmount, String transactionDate)
    {
    	 List<String> returnStr= new ArrayList();
        try {
			int  result = service.createPurchaseHistories(user_id,pharmacyID,MaskId,transactionAmount,transactionDate);
			if(result == 1)
			{
				returnStr.add("Success");
			}
			else
			{
				returnStr.add("Fail");
			}
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnStr.add("Fail " + e);
		}

       
        
        return new ResponseEntity<List<String>>(returnStr, HttpStatus.OK);
    }
}




