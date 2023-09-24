package com.example.doris.service;

import com.example.doris.entity.DorisUser;

import java.util.List;

public interface DorisService {

    /**
     * 查询所有的表数据
     *
     * @return
     */
    public List<DorisUser> listDoris();

    DorisUser quryDoris(int id);

    void updateDoris(int id, String name);
}
