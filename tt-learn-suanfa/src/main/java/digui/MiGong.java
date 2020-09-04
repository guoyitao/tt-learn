package digui;

/**
 * description: 二位数组，迷宫，递归走迷宫
 * author: Guo Yitao
 * create: 2020-07-25 14:23
 **/
public class MiGong {

	public static void main(String[] args) {
		int[][] map = new int[8][7];

		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}

		map[3][1] = 1;
		map[3][2] = 1;

		//挡板 可以出3
//		map[1][2] = 1;
//		map[2][2] = 1;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println();
		}

		setWay(map,1,1);
		System.out.println("--------------------------------------");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println();
		}
	}

	/**
	 * [6][5]表示出口，1位墙
	 * 策略: 下->右->上->左
	 * @date 2020/7/25 14:44
	 * @author guoyitao
	 * @param map 地图
	 * @param i 入口
	 * @param j
	 * @return boolean
	 */
	public static boolean setWay(int[][] map, int i, int j) {
		if (map[6][5] == 2){
			return true;
		}else {
			if (map[i][j] == 0){
				//下->右->上->左
				map[i][j] = 2;
				if (setWay(map,i+1,j)){
					return true;
				} else if (setWay(map,i,j+1)){
					return true;
				} else if (setWay(map,i-1,j)){
					return true;
				}else if (setWay(map,i,j-1)){
					return true;
				} else {
					//死路
					map[i][j] = 3;
					return false;
				}
			} else {  //map【i】【j】 可能是1,2,3
				return false;
			}
		}
	}
}
