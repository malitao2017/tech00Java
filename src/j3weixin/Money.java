package j3weixin;

import java.util.Random;
/**
 * 微信红包
 * @author malitao
 *
 */
public class Money {

	/*著作权归作者所有。
	商业转载请联系作者获得授权，非商业转载请注明出处。
	作者：陈鹏
	链接：https://www.zhihu.com/question/22625187/answer/85530416
	来源：知乎*/
	public static double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
	    // remainSize 剩余的红包数量
	    // remainMoney 剩余的钱
	    if (_leftMoneyPackage.remainSize == 1) {
	        _leftMoneyPackage.remainSize--;
	        return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
	    }
	    Random r     = new Random();
	    double min   = 0.01; //
	    double max   = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
	    double money = r.nextDouble() * max;
	    money = money <= min ? 0.01: money;
	    money = Math.floor(money * 100) / 100;
	    _leftMoneyPackage.remainSize--;
	    _leftMoneyPackage.remainMoney -= money;
	    return money;
	} 
}
class LeftMoneyPackage{
	int remainSize;
	int remainMoney;
}
