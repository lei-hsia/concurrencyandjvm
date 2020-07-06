// 输入描述:
// 第一行包含一个正整数N，代表测试用例的个数。

// 每个测试用例的第一行包含一个正整数M，代表视频的帧数。

// 接下来的M行，每行代表一帧。其中，第一个数字是该帧的特征个数，接下来的数字是在特征的取值；比如样例输入第三行里，2代表该帧有两个猫咪特征，<1，1>和<2，2>
// 所有用例的输入特征总数和<100000

// N满足1≤N≤100000，M满足1≤M≤10000，一帧的特征个数满足 ≤ 10000。
// 特征取值均为非负整数。
// 输出描述:
// 对每一个测试用例，输出特征运动的长度作为一行
// 示例1
// 输入
// 复制
// 1
// 8
// 2 1 1 2 2
// 2 1 1 1 4
// 2 1 1 2 2
// 2 2 2 1 4
// 0
// 0
// 1 1 1
// 1 1 1
// 输出
// 复制
// 3
// 说明
// 特征<1,1>在连续的帧中连续出现3次，相比其他特征连续出现的次数大，所以输出3
// 备注:
// 如没有长度大于2的特征运动，返回1

#include <iostream>
#include <map>
using namespace std;

int main() {
    int n, m;
    cin >> n;
    
    int len;
    pair<int, int> xy;  // 每个block中每个pair重用
    
    while(n--) {
        cin >> m;
        
        int res = 0;  // 每个block记录一个res
        map<pair<int, int>, int> preCount;
        map<pair<int, int>, int> count;
        
        while(m--){
            cin >> len;
            for(int i = 0;  i < len; ++i) {
                cin >> xy.first >> xy.second;
                
                if (preCount.count(xy)) count[xy] = preCount[xy]+1;
                else count[xy] = 1;
                
                if (count[xy] > res) res = count[xy];
            }
            preCount.clear();
            preCount.swap(count);
        }
        cout << res << endl;
    }
    return 0;
}