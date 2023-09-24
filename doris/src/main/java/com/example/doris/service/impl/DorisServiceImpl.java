package com.example.doris.service.impl;

import com.example.doris.entity.DorisUser;
import com.example.doris.mapper.DorisMapper;
import com.example.doris.service.DorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DorisServiceImpl implements DorisService {

    @Autowired
    DorisMapper dorisMapper;

    @Override
    public List<DorisUser> listDoris() {
        return dorisMapper.listDoris();
    }

    @Override
    public DorisUser quryDoris(int id) {
        return dorisMapper.quryDoris(id);
    }

    @Override
    public void updateDoris(int id, String name) {
        dorisMapper.updateDoris(id, name);
    }
}