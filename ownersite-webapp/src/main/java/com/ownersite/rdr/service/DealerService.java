/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.List;

import com.ownersite.rdr.dto.DealerDTO;

/**
 * @author polamred
 *
 */
public interface DealerService {

	public List<DealerDTO> getAllDealers();
	
	public DealerDTO getDealerById(long dealerId);
}
