package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/pharmacy")
public class PharmacyController {
	@Autowired
    PharmacyService service;
 
    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAll() {
        List<Pharmacy> list = service.getAll();
        return new ResponseEntity<List<Pharmacy>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy>  getPharmacyById(@PathVariable("id") Integer id) 
    {
        Pharmacy entity = service.getPharmacyById(id);
        return new ResponseEntity<Pharmacy>(entity, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<Pharmacy>> findByName(@PathVariable("name") String name) {
        List<Pharmacy> list = service.getPharmacyByName(name);
        return new ResponseEntity<List<Pharmacy>>(list, HttpStatus.OK);
    }
    //查詢星期和時間
    @GetMapping("/searchOpenweek")
    public ResponseEntity<List<Pharmacy>> findByOpenweek( @RequestParam(required = false) String openweek ,String opentime )
    {
    	List<Pharmacy> list = service.findPharmacyByOpenweek(openweek,opentime);
        return new ResponseEntity<List<Pharmacy>>(list, HttpStatus.OK);
    }

    //查詢時間
    @GetMapping("/searchOpentime")
    public ResponseEntity<List<Pharmacy>> findPharmacyByOpentime( @RequestParam(required = false) String opentime )
    {
    	List<Pharmacy> list = service.findPharmacyByOpentime(opentime);
        return new ResponseEntity<List<Pharmacy>>(list, HttpStatus.OK);
    }
    
    //查詢有Ｘ價格以下的口罩的藥局
    @GetMapping("/searchMaskPrice")
    public ResponseEntity<List<Pharmacy>> findPharmacyByMaskPrice( @RequestParam(required = false) BigDecimal price )
    {
    	List<Pharmacy> list = service.findPharmacyByMaskPrice(price);
        return new ResponseEntity<List<Pharmacy>>(list, HttpStatus.OK);
    }
    

    //依照關鍵字查詢口罩名稱及藥局名稱，藥局且口罩名稱都有關鍵字者排前面，再來是藥局名稱與口罩名稱
    @GetMapping("/searchKeyWord")
    public ResponseEntity<List<PharmacyMask>> findPharmacyandMaskByKeyWord( @RequestParam(required = false) String keyWord )
    {
    	List<PharmacyMask> list = service.findPharmacyandMaskByKeyWord(keyWord);
        return new ResponseEntity<List<PharmacyMask>>(list, HttpStatus.OK);
    }
    

    //更新藥局資料
    @PutMapping("/{id}")
    public ResponseEntity<Pharmacy> UpdateMask(Pharmacy pharmacy, @PathVariable("id") Integer id)
    {
    	Pharmacy updateMask = service.updatePharmacy(pharmacy, id);
        return new ResponseEntity<Pharmacy>(updateMask, HttpStatus.OK);
    }
}




