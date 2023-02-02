package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.MissingDao;
import com.lovepet.animal.dao.PublishDao;
import com.lovepet.animal.dto.MissingAnimalRequest;
import com.lovepet.animal.dto.PublishAnimalRequest;
import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.model.PublishData;
import com.lovepet.animal.service.MissingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;


@Component
public class MissingServiceImpl implements MissingService {
    @Autowired
    private MissingDao missingDao;

    @Override
    public void createMissing(MissingAnimalRequest missingAnimalRequest) {
        try {
            InputStream fis = missingAnimalRequest.getMissingAnimalPhoto().getInputStream();
            String path = String.format("D:/animal/src/main/resources/static/images/missing/%s", missingAnimalRequest.getMissingId() + ".jpg");
            FileOutputStream fos = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        missingDao.createMissing(missingAnimalRequest);
    }

    @Override
    public List<MissingData> getMissingById(Integer id) {
        return missingDao.getMissingById(id);
    }
}
