package com.aladdinworks5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks5.domain.Asset;
import com.aladdinworks5.dto.AssetDTO;
import com.aladdinworks5.dto.AssetSearchDTO;
import com.aladdinworks5.dto.AssetPageDTO;
import com.aladdinworks5.dto.AssetConvertCriteriaDTO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AssetService extends GenericService<Asset, Integer> {

	List<Asset> findAll();

	ResultDTO addAsset(AssetDTO assetDTO, RequestDTO requestDTO);

	ResultDTO updateAsset(AssetDTO assetDTO, RequestDTO requestDTO);

    Page<Asset> getAllAssets(Pageable pageable);

    Page<Asset> getAllAssets(Specification<Asset> spec, Pageable pageable);

	ResponseEntity<AssetPageDTO> getAssets(AssetSearchDTO assetSearchDTO);
	
	List<AssetDTO> convertAssetsToAssetDTOs(List<Asset> assets, AssetConvertCriteriaDTO convertCriteria);

	AssetDTO getAssetDTOById(Integer assetId);







}





