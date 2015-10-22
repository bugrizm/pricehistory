/**
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.price.history;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unchecked")
public class MainRestController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@RequestMapping(value = "/search/{searchWords}", method = RequestMethod.GET)
	public List<Object> search(@PathVariable("searchWords") String searchWords) throws IOException {
		
		String[] words = searchWords.split("\\+");
		
		String sql = "SELECT p.id, p.name, p.link, "
						+ "(select pp.price from ProductPrice pp where pp.id = (select max(pp2.id) from ProductPrice pp2 where pp2.product.id = pp.product.id) and pp.product.id = p.id), p.store.id "
						+ "FROM Product p WHERE p.active=true ";
		
		for(int i=0; i<words.length; i++) {
			sql += " AND p.name like ?";
		}
		
		Query query = entityManager.createQuery(sql);
		
		for(int i=0; i<words.length; i++) {
			query.setParameter(i+1, "%" + words[i] + "%"); 
		}
		
		return query.getResultList();
	}
	
	@RequestMapping(value = "/priceHistory/{productId}", method = RequestMethod.GET)
	public List<Object> retrievePriceHistory(@PathVariable("productId") Integer productId) throws IOException {
		return entityManager.createQuery("select pp.date, pp.price from ProductPrice pp where pp.product.id = :productId").setParameter("productId", productId).getResultList();
	}
	
	/*
	 * parsedProductPrice.setDate(parseDate(jsonObject.getString(0)));
                parsedProductPrice.setPrice(new BigDecimal(parsePrice(jsonObject.getInt(1))));
	 */
	
	
}