package epi;

import java.util.ArrayList;
import java.util.List;

class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> mem=new ArrayList<>();
        
        mem.add(new ArrayList<>());
        mem.get(0).add(triangle.get(0).get(0));
        
        mem.add(new ArrayList<>());
        mem.get(1).add(triangle.get(0).get(0)+triangle.get(1).get(0));
        mem.get(1).add(triangle.get(0).get(0)+triangle.get(1).get(1));
        for(int i=2;i<triangle.size();i++){
            mem.add(new ArrayList<>());
            mem.get(i).add(mem.get(i-1).get(0)+triangle.get(i).get(0));
            int j=1;
            for(;j<triangle.get(i).size()-1;j++){
                mem.get(i).add(Math.min(mem.get(i-1).get(j-1),mem.get(i-1).get(j))+triangle.get(i).get(j));
            }
            mem.get(i).add(mem.get(i-1).get(j-1)+triangle.get(i).get(j));
        }
        StringBuilder appendStr=new StringBuilder("");
        //appendStr.append(".").append(key).toString();
        //str.
        int min=Integer.MAX_VALUE;
        for(int i=0;i<mem.get(mem.size()-1).size();i++){
            if(mem.get(mem.size()-1).get(i)<min){
                min=mem.get(mem.size()-1).get(i);
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
//		int[][] triangle={
//		          {2},
//		          {3,4},
//		         {6,5,7},
//		        {4,1,8,3}
//    };
		List<List<Integer>> triangle=new ArrayList<>();
		for(int i=0;i<4;i++){
			triangle.add(new ArrayList<>());
		}
		triangle.get(0).add(2);
		triangle.get(1).add(3);
		triangle.get(1).add(4);
		triangle.get(2).add(6);
		triangle.get(2).add(5);
		triangle.get(2).add(7);
		triangle.get(3).add(4);
		triangle.get(3).add(1);
		triangle.get(3).add(8);
		triangle.get(3).add(3);
		
		System.out.println(new Triangle().minimumTotal(triangle));
	}
}