package dbsecure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration //내가 설정 클래스입니다
@EnableEncryptableProperties //현재 정보를 application.properties파일에서 사용 가능 
public class DBConfig {
	@Bean("jasyptEncryptor") //메소드 리턴 객체 주입
	public StringEncryptor  stringEncryptor(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getenv("DB_PASSWORD"));
        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        System.out.println("===DBConfig 출력===");
        System.out.println(System.getenv("DB_PASSWORD"));//1234
        System.out.println
        (encryptor.decrypt("aFKkUFWbjmmtbS5mF6vt4H42hTjDp2t0k+FnJhfgcbc="));
        System.out.println
        (encryptor.decrypt("f07s3VCB4YnPSTyAm6h1tfIEGy6lGfQas99ShJi25idLewFNVT4bm3apUXgj5AtfNGIctIgvy9dkJU+kjJQ+qvcPyshtjp5B3WQQRhPJuFcsryCphfyjRP7h/Bxn3LP5duX939d+zKw="
        		));
        System.out.println(encryptor.decrypt("UE5QeHeLGBATuU3Ntcf9/arNkvHzNwoQ"));
        System.out.println(encryptor.decrypt("8l0YUUP5b+CaqqMWXPCAsYbPFi8ZwB1C"));
        
        /*ENC(aFKkUFWbjmmtbS5mF6vt4H42hTjDp2t0k+FnJhfgcbc=)
ENC(f07s3VCB4YnPSTyAm6h1tfIEGy6lGfQas99ShJi25idLewFNVT4bm3apUXgj5AtfNGIctIgvy9dkJU+kjJQ+qvcPyshtjp5B3WQQRhPJuFcsryCphfyjRP7h/Bxn3LP5duX939d+zKw=)
ENC(UE5QeHeLGBATuU3Ntcf9/arNkvHzNwoQ)
ENC(8l0YUUP5b+CaqqMWXPCAsYbPFi8ZwB1C)*/
        return encryptor;
        
        
	}
}
