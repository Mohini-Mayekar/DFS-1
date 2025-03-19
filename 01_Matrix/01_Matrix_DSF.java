/* Time Complexity : O(m*n) 
 *  m - rows of the matrix - mat
 *  n - cols of the matrix - mat */
/* Space Complexity : O(m*n)
*  m - rows of the matrix - mat
*  n - cols of the matrix - mat */

class Solution {
    int[][] dp;
    int m;
    int n;    
    public int[][] updateMatrix(int[][] mat) {                
        this.m = mat.length;
        this.n = mat[0].length;
        this.dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] != 0){                    
                    mat[i][j] = -1;
                }
            }            
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == -1){                
                    dp[i][j] = dfs(mat, i, j);
                }
            }            
        }
        
        return dp;
    }

    private int dfs(int[][] mat, int i, int j){
        //base
        if(i < m - 1 && mat[i+1][j] == 0) return 1;
        if(j < n - 1 && mat[i][j + 1] == 0) return 1;
        if(i > 0 && mat[i-1][j] == 0) return 1;
        if(j > 0 && mat[i][j-1] == 0) return 1;

        //logic
        int top = Integer.MAX_VALUE - 5;
        int left = Integer.MAX_VALUE - 5;
        int right = Integer.MAX_VALUE - 5;
        int bottom = Integer.MAX_VALUE - 5;    

        if(i > 0 && dp[i-1][j] != 0){
            top = dp[i-1][j];
        }

        if(j > 0 && dp[i][j-1] != 0){
            left = dp[i][j-1];
        }

        if(j < n-1){
            if(dp[i][j+1] == 0){
                dp[i][j+1] = dfs(mat, i, j + 1);
            }
            right = dp[i][j+1];
        }

        if(i < m-1){
            if(dp[i+1][j] == 0){
                dp[i+1][j] = dfs(mat, i + 1, j);
            }
            bottom = dp[i+1][j];
        }

        return Math.min(Math.min(left,right), Math.min(top, bottom)) + 1;
    }
}