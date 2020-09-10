package leetcode;

import javafx.collections.transformation.SortedList;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class L332 {
    private interface Solution {
        List<String> findItinerary(List<List<String>> tickets);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();

        assertEquals(Arrays.asList("JFK","NRT","JFK","KUL"), solution.findItinerary(Arrays.asList(Arrays.asList("JFK","KUL"),
                Arrays.asList("JFK","NRT"),
                Arrays.asList("NRT","JFK"))));
        assertEquals(Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"), solution.findItinerary(Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"))));
        assertEquals(Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"), solution.findItinerary(Arrays.asList(Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO") )));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<String> findItinerary(List<List<String>> tickets) {
            String start = "JFK";
            Map<String,LinkedList<String>> map = new HashMap<>();
            for (List<String> ticket : tickets) {
                LinkedList<String> list = map.computeIfAbsent(ticket.get(0), k -> new LinkedList<>());
                list.add(ticket.get(1));
            }
            map.forEach((k,v)->{
                Collections.sort(v);
            });
            List<String> res = new ArrayList<>(tickets.size()+1);
            res.add(start);
            findPath(res,map,start,tickets.size());


            return res;
        }
        boolean findPath(List<String>ans,Map<String,LinkedList<String>>map,String start,int deep){
            LinkedList<String> strings = map.get(start);
            if (strings==null||strings.isEmpty())return deep==0;

            for (int i = 0; i < strings.size(); i++) {
                String next = strings.get(i);
                ans.add(next);
                strings.remove(i);
                if (findPath(ans,map,next,deep-1)){
                    return true;
                }
                ans.remove(ans.size()-1);
                strings.add(i,next);
            }
            return false;
        }
    }
}