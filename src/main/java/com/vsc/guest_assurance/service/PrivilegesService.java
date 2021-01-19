package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.Constant;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.dao.PrivilegesMapper;
import com.vsc.guest_assurance.entity.LoginTokens;
import com.vsc.guest_assurance.entity.Privileges;
import com.vsc.guest_assurance.vo.BPrivilegeItemVo;
import com.vsc.guest_assurance.vo.BPrivilegeListVo;
import com.vsc.guest_assurance.vo.BPrivilegeTypeVo;
import com.vsc.guest_assurance.vo.BPrivilegeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class PrivilegesService {
    @Autowired
    private PrivilegesMapper privilegesMapper;


    /**
     * 根据id取没有的权限
     */
    public List<Privileges> getUnauthorizedPrivilegeByIds(String[] ids) {
        if (ids == null || ids.length == 0) {
            ids = new String[]{"-1"};
        }
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(Integer.parseInt(id));
        }
        return privilegesMapper.selectUnAchievedByIds(idList);
    }

    /**
     * 获取权限集合
     *
     * @param privilegeIds
     * @param type
     * @param ids
     * @return
     */
    public List<BPrivilegeVo> selectPrivileges(String privilegeIds, String type, List<Integer> ids, Integer language) {
        return privilegesMapper.selectPrivileges(privilegeIds, type, ids, language);
    }

    /**
     * 获取权限选项
     * @param customerFlag
     * @return
     */
    public BPrivilegeTypeVo selectByCustomerFlag(Integer customerFlag, Integer language){

        List<BPrivilegeVo> bPrivilegeVoList= privilegesMapper.selectByCustomerFlag(customerFlag, language);

        //按权限子类型分组
        Map<Integer, List<BPrivilegeVo>> bPrivilegeVoMap = bPrivilegeVoList.stream()
                .collect(Collectors.groupingBy(BPrivilegeVo::getNameId));
        List<Integer> nameIdList = bPrivilegeVoMap.keySet().stream()
                .sorted(Comparator.comparing(Integer::intValue))
                .collect(Collectors.toList());
        List<BPrivilegeItemVo> bPrivilegeItemVoList = new ArrayList<>();
        for(Integer nameId : nameIdList){
            BPrivilegeItemVo bPrivilegeItemVo = new BPrivilegeItemVo();
            bPrivilegeItemVo.setType(bPrivilegeVoMap.get(nameId).get(0).getType());
            bPrivilegeItemVo.setGenreId(bPrivilegeVoMap.get(nameId).get(0).getGenreId());
            bPrivilegeItemVo.setGenre(bPrivilegeVoMap.get(nameId).get(0).getGenre());
            bPrivilegeItemVo.setNameId(bPrivilegeVoMap.get(nameId).get(0).getNameId());
            bPrivilegeItemVo.setName(bPrivilegeVoMap.get(nameId).get(0).getName());
            bPrivilegeItemVo.setbPrivilegeVoList(bPrivilegeVoMap.get(nameId));
            bPrivilegeItemVoList.add(bPrivilegeItemVo);
        }
        //按权限类型分组
        Map<Integer, List<BPrivilegeItemVo>> bPrivilegeItemVoMap = bPrivilegeItemVoList.stream()
                .collect(Collectors.groupingBy(BPrivilegeItemVo::getGenreId));
        List<Integer> genreIdList = bPrivilegeItemVoMap.keySet().stream()
                .sorted(Comparator.comparing(Integer::intValue))
                .collect(Collectors.toList());
        List<BPrivilegeListVo> bPrivilegeListVoList = new ArrayList<>();
        for(Integer genreId : genreIdList){
            BPrivilegeListVo bPrivilegeListVo = new BPrivilegeListVo();
            bPrivilegeListVo.setType(bPrivilegeItemVoMap.get(genreId).get(0).getType());
            bPrivilegeListVo.setGenreId(bPrivilegeItemVoMap.get(genreId).get(0).getGenreId());
            bPrivilegeListVo.setGenre(bPrivilegeItemVoMap.get(genreId).get(0).getGenre());
            bPrivilegeListVo.setPrivilegeItemVoList(bPrivilegeItemVoMap.get(genreId));
            bPrivilegeListVoList.add(bPrivilegeListVo);
        }

        //按前后台分组
        BPrivilegeTypeVo bPrivilegeTypeVo = new BPrivilegeTypeVo();
        bPrivilegeTypeVo.setbPrivilegeListVoListBackend(bPrivilegeListVoList.stream()
                .filter(p->p.getType().equals(Constant.TOKEN_TYPE_BACKEND))
                .collect(Collectors.toList()));
        bPrivilegeTypeVo.setbPrivilegeListVoListApp(bPrivilegeListVoList.stream()
                .filter(p->p.getType().equals(Constant.TOKEN_TYPE_APP))
                .collect(Collectors.toList()));

        return bPrivilegeTypeVo;
    }

    /**
     * 校验权限字符合理性
     * 客户账号角色只能选有客户标识的权限
     * @param privilegeIds
     * @param customerFlag
     * @param loginTokens
     */
    public void checkPrivilegeIds(String privilegeIds, boolean customerFlag, LoginTokens loginTokens) {
        if (StringUtils.isBlank(privilegeIds)) {
            return;
        }
        //检查是否均为数字
        List<Integer> idList = new ArrayList<>();
        for (String id : privilegeIds.split(",")) {
            if (!StringUtils.isNumeric(id)) {
                throw new ApiException(MessageCode.CODE_PARAMETER_ERROR);
            }
            idList.add(Integer.parseInt(id));
        }
        //检查数据存在性数量相符
        Integer privilegeNum = privilegesMapper.selectByIds(customerFlag, idList);
        if (idList.size() != privilegeNum) {
            throw new ApiException(MessageCode.CODE_PARAMETER_ERROR);
        }
    }
}
