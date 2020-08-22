package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L93 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(Wrapper.stringToArray("[\"255.255.11.135\", \"255.255.111.35\"]"), solution.restoreIpAddresses("25525511135"));
        assertEquals(Wrapper.stringToArray("[\"0.10.0.10\",\"0.100.1.0\"]"), solution.restoreIpAddresses("010010"));
        assertEquals(Wrapper.stringToArray("[\"0.0.0.0\"]"), solution.restoreIpAddresses("0000"));

    }

    private static class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> list = new ArrayList<>();
            split(s, 0, 4, null, 0, list);
            return list;
        }

        public void split(String s, int index, int level, char[] sb, int sbLen, List<String> list) {
            char c;
            int t = 0;
            if (index == s.length()) return;
            if (level == 1) {
                sb[sbLen++] = '.';
                while (index < s.length()) {
                    c = s.charAt(index++);
                    t += c - '0';
                    sb[sbLen++] = c;
                    if (t==0){
                        if (index == s.length())break;
                        return;
                    }
                    if (t > 255) return;
                    t *= 10;
                }
                list.add(new String(sb));
                return;
            }
            if (sb == null) sb = new char[s.length() + 3];
            else sb[sbLen++] = '.';


            while (index < s.length() && (t = t + ((c = s.charAt(index++)) - '0')) < 256) {
                sb[sbLen++] = c;
                if (t==0){
                    split(s, index, level - 1, sb, sbLen, list);
                    break;
                }
                split(s, index, level - 1, sb, sbLen, list);
                t *= 10;
            }

        }

    }
}