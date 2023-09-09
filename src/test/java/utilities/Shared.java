package utilities;

public class Shared {

	public boolean isArraysConcurrent(String[] arr1, String[] arr2) {
		if(arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i].equals(arr2[j]))
					break;
				else if (j == arr2.length - 1)
					return false;
			}
		}
		return true;
	}
	
//	public static void main(String[] args) {
//		String[] x = {"1","3", "2"};
//		String[] y = {"1","2","3"};
//		System.out.println(isArraysConcurrent(x,y));
//		
// 	}

}
