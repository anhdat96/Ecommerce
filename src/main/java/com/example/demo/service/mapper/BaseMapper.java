package com.example.demo.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public abstract class BaseMapper {
    protected final ModelMapper modelMapper = new ModelMapper();

    /**
     * @param origin   đối tượng chứa data cần transfer
     * @param destType đối tượng cần lấy class để transfer
     * @param <O>      class của đối tượng origin
     * @param <D>      class của dối tượng destination
     * @return đối tượng destination có chứa data của đối tượng origin
     */
    public <O, D> D tranferData(O origin, Type destType) {
        if (null == origin) return null;

        return modelMapper.map(origin, destType);
    }

    /**
     * @param id       list id của đối tượng cần get
     * @param repo     repository của đối tượng cần get
     * @param destType đối tượng cung cấp class cần transfer data
     * @param <D>      class cần transfer data
     * @param <O>      class được transfer data
     * @param <R>      loại repository
     * @return đối tượng destination
     */
    public <D, O, R extends JpaRepository<O, Long>> D getDataById(Long id, R repo, Type destType) {
        if (null == id) return null;

        Optional<O> opt = repo.findById(id);
        if (opt.isPresent()) {
            O origin = opt.get();
            return this.tranferData(origin, destType);
        } else {
            return null;
        }
    }

    public <D, O, R extends JpaRepository<O, Long>> Set<D> getSetDataByIds(List<Long> ids, R repo, Type destType) {
        ids = null == ids ? new ArrayList<>() : ids;
        Set<D> set = new HashSet<>();

        for (long id : ids) {
            set.add(this.getDataById(id, repo, destType));
        }

        return set;
    }

    public <O, D> Set<D> tranferSetData(Set<O> orginList, Type destType) {
        Set<D> set = new HashSet<>();

        for (O origin : orginList) {
            set.add(this.tranferData(origin, destType));
        }

        return set;
    }

    public<I> void configDisplayAllInformationDTO(I input, final Type baseType, final Type abstractType){
        for (Field field : input.getClass().getDeclaredFields()){
            try{

            }catch (Exception e){

            }
        }

        // get 'field' là các thuộc tính của input
        // 'field' cast được sang dạng baseType pk?
        // đúng thì set 'field' là null

        // sai thì:
            // 'field' là đạng array/collection/... pk?
            // đúng thì get 'ele' là một phần tử của 'field'
            // 'ele' cast được sang dạng baseType pk?
                // đúng thì set 'field' là tập rỗng (thông thường là new HashSet())

                // sai thì call configDisplayAllInformationDTO(ele, baseType, abstractType) - chú ý input của method là ele

            // sai thì call configDisplayAllInformationDTO(field, baseType, abstractType) - chú ý input của method là ele
    }
}
