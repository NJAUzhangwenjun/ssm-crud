package cn.wen.crud.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张文军
 * @Description:mybatis自动生成
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/251:07
 */
public class MBGTest {
		public static void main(String[] args) throws Exception{
				List<String> warnings = new ArrayList<String>();
				boolean overwrite = true;
				File configFile = new File("mbg.xml");
				ConfigurationParser cp = new ConfigurationParser(warnings);
				Configuration config = cp.parseConfiguration(configFile);
				DefaultShellCallback callback = new DefaultShellCallback(overwrite);
				MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
				myBatisGenerator.generate(null);
		}
}
