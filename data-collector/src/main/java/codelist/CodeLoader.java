package codelist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class CodeLoader implements InitializingBean {
    private List<String> codeListAll = new ArrayList<String>();

    private Resource[] codeFiles;

    // @Autowired
    @javax.annotation.Resource(name = "code_list")
    private Properties code_list_prop;

    public CodeLoader() {
        super();
    }
/**
 * 忽略列数不为2的行，文件名(不含后缀)+第一列形成code.
 * **/
    public void afterPropertiesSet() throws Exception {
        if (codeFiles == null || codeFiles.length == 0) {
            return;
        }
        for (Resource rsc : codeFiles) {
            BufferedReader in = new BufferedReader(new InputStreamReader(rsc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null && !inputLine.startsWith("#")) {
                String[] lineElements = inputLine.split("\t");
                if (lineElements.length == 2) {
                    String prefix = code_list_prop.getProperty(rsc.getFilename());
                    codeListAll.add(prefix + lineElements[0]);
                }
            }
        }
    }

    public List<String> getCodeListAll() {
        return codeListAll;
    }

    public void setCodeListAll(List<String> codeListAll) {
        this.codeListAll = codeListAll;
    }

    public Properties getCode_list_prop() {
        return code_list_prop;
    }

    public void setCode_list_prop(Properties code_list_prop) {
        this.code_list_prop = code_list_prop;
    }

    public void setCodeFiles(Resource[] codeFiles) {
        this.codeFiles = codeFiles;
    }
}