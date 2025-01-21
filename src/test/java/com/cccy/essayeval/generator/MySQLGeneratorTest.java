package com.cccy.essayeval.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import org.junit.jupiter.api.Test;

/**
 * MySQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/wx_essayeval?serverTimezone=Asia/Shanghai", "cccy", "718235")
            .schema("baomidou")
            .build();

    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        //包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.cccy") // 指定父包名
                .moduleName("essayeval") // 指定模块名
                .build();
        generator.packageInfo(packageConfig);
        generator.strategy(strategyConfig().build());
        generator.global(globalConfig().build());
        generator.execute();
    }
}