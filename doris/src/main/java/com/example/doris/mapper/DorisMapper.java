package com.example.doris.mapper;

import com.example.doris.entity.DorisUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DorisMapper {

    /**
     * 查询所有的doris 数据
     *
     * @return
     */
    List<DorisUser> listDoris();

    DorisUser quryDoris(int id);

    DorisUser updateDoris(int id, String name);
}