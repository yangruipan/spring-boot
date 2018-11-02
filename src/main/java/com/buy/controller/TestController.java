package com.buy.controller;

import com.buy.config.RedisService;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName TestController
 * @Author yrp
 * @Date 2018/11/1 11:45
 */
@RestController
public class TestController {

    @Autowired
    private RedisService redisService;

    private Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    public static void main(String[] args){
        String imgPath = "F:/二维码和条形码/two.png";
        try {
            new TestController().decode(imgPath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据二维码或者条形码图片进行解析，获取里面的信息
     * @param imgPath 图片的绝对路径
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/decode.do")
    public String decode(String imgPath) throws Exception {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                LOGGER.error("the decode image may be not exit.");
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, String> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            result = new MultiFormatReader().decode(bitmap, hints);
            LOGGER.info("***************************************************************************");
            LOGGER.info("*************************执行redis存储**************************************");
            redisService.addStr("123",result.getText());
            LOGGER.info("条形码解析结果："+result.getText());
            LOGGER.info("获取redis数据"+redisService.get("123"));
            LOGGER.info("***************************************************************************");
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
