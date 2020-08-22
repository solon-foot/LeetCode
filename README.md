### 项目介绍
记录刷题

### 怎么刷LeetCode
1. 工具 idea
2. 修改 settings中的 `File and Code TempLates` 添加LeetCode 模本
```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.junit.Test;
import static org.junit.Assert.*;

/**
*
*/
#parse("File Header.java")
public class ${NAME}
{
@Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true,true);
    }

    private static class Solution {

    }
}
```
