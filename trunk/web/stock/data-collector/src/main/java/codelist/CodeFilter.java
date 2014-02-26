package codelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("codeFilter")
public class CodeFilter {
	@Autowired
	CodeLoader siteLive;
    private  Map<String, List<String>> partList = new HashMap<String, List<String>>();

    private  List<String> getCodeListByKey(String key){
        if(partList.get(key) != null){
            return partList.get(key);
        }
        List<String> retList = new ArrayList<String>();
        for(String code:siteLive.getCodeListAll()){
            if(code.startsWith(key)){
                retList.add(code);
            }
        }
        partList.put(key, retList);
        return retList;
    }
    /**获取以key打头的code列表，格式为文件名（经过属性文件props->code_list.properites转换为小写）+code比如sh600,sz00**/
	public List<String> getByKey(String key){
		return getCodeListByKey(key);
	}
}
