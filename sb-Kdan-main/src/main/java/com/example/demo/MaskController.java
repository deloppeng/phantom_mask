package com.example.demo;

import java.util.ArrayList;
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
@RequestMapping(value = "/mask")
public class MaskController {
	@Autowired
	MaskService service;
 
    @GetMapping
    public ResponseEntity<List<Mask>> getAll() {
        List<Mask> list = service.getAll();
        return new ResponseEntity<List<Mask>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mask>  getMaskById(@PathVariable("id") Integer id) 
    {
        Mask entity = service.getMaskById(id);
        return new ResponseEntity<Mask>(entity, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<Mask>> findByName(@PathVariable("name") String name) {
        List<Mask> list = service.getMaskByName(name);
        return new ResponseEntity<List<Mask>>(list, HttpStatus.OK);
    }


    @GetMapping("/find/pharmacy/{name}")
    public ResponseEntity<List<Mask>> findMaskByPharmacyName(@PathVariable("name") String name) {
        List<Mask> list = service.findMaskByPharmacyName(name);
        return new ResponseEntity<List<Mask>>(list, HttpStatus.OK);
    }
    

    //更新口罩資料
    @PutMapping("/{id}")
    public ResponseEntity<Mask> UpdateMask(Mask mask, @PathVariable("id") Integer id)
    {
    	Mask updateMask = service.updateMask(mask, id);
        return new ResponseEntity<Mask>(updateMask, HttpStatus.OK);
    }
 

    //刪除口罩資料
    @DeleteMapping("/{id}")
    public ResponseEntity<List<String>> deleteEmployeeById(@PathVariable("id") Integer id, String name) {
        String resultStr = service.deleteMaskByMaskNameAndPharmacyId(name ,id);
        
        List<String> returnStr= new ArrayList();
        returnStr.add(resultStr);
        return new ResponseEntity<List<String>>(returnStr, HttpStatus.OK);
    }
    
}




