# MiniGames
android小游戏-扫雷。kotlin实现
### 1.雷的生成是在点击第一个格子的时候随机生成的，可以保证第一个格子永不踩雷
### 2.用给每一颗雷的周围8个格子增加雷计数的方式来确定每个格子周围的雷数
### 3.点击到空白格的时候，采用 ***深度优先*** 算法扩散至边缘或者有雷计数点。

![图一](https://github.com/772865705/MiniGames/blob/master/res_for_readme/swipe1.png)
![图二](https://github.com/772865705/MiniGames/blob/master/res_for_readme/swipe2.png)
