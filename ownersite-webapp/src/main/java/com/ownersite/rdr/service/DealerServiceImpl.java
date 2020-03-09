/**
 * 
 */
package com.ownersite.rdr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownersite.rdr.dto.DealerDTO;
import com.ownersite.rdr.entity.Dealer;
import com.ownersite.rdr.repository.DealerJpaRepository;

/**
 * @author polamred
 *
 */
@Service
public class DealerServiceImpl implements DealerService {
	
	
	
	private final DealerJpaRepository dealerJpaRepository;
	
	@Autowired
	public DealerServiceImpl(DealerJpaRepository dealerJpaRepository){
		this.dealerJpaRepository = dealerJpaRepository;
		}

	/* (non-Javadoc)
	 * @see com.ownersite.rdr.service.DealerService#getAllDealers()
	 */
	@Override
	public List<DealerDTO> getAllDealers() {
		List<Dealer> dealerList = dealerJpaRepository.findAll();
		
		List<DealerDTO> delaerDTOList = new ArrayList<>();
		for (Dealer dealer: dealerList){
			delaerDTOList.add(makeDelaerDTO(dealer));
		}
		return delaerDTOList;
	}

	/* (non-Javadoc)
	 * @see com.ownersite.rdr.service.DealerService#getDealerById(long)
	 */
	@Override
	public DealerDTO getDealerById(long dealerId) {
		Dealer dealer = dealerJpaRepository.findByDealerId(dealerId);
		return makeDelaerDTO(dealer);
	}
	
	private DealerDTO makeDelaerDTO(Dealer dealer){
		DealerDTO dealerDTO = new DealerDTO();
		dealerDTO.setDealerId(dealer.getId());
		dealerDTO.setFirstname(dealer.getFirstname());
		dealerDTO.setLastname(dealer.getLastname());
		dealerDTO.setMobile(dealer.getMobile());
		dealerDTO.setState(dealer.getState());
		dealerDTO.setCity(dealer.getCity());
		dealerDTO.setCountry(dealer.getCountry());
		dealerDTO.setAddress(dealer.getAddress());
		dealerDTO.setEmail(dealer.getEmail());
		return dealerDTO;
	}

}
