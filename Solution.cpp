#include<bits/stdc++.h>
using namespace std;
#define int long long

int a,p;
int euler_value;
int exponent;
int single_primordial_root;
vector<int> primordial_roots;


inline void write(int x) {
	if (x < 0) {
		putchar('-');
		x = -x;
	}
	if (x > 9) {
		write(x / 10);
	}
	putchar(x % 10 + '0');
}
int gcd(int a,int b)
{
	if(b==0)
	{
		return a;
	}
	return gcd(b,a%b);
}
//判断是否为奇素数
int judge_valid(int x)
{
	int flag=1;
	int y=sqrt(x);
   	for (int i=2;i<=y;i++)
   	{
	    if (x%i==0)
		{
//			如果是合数则不合法
	        flag=0;
	        break;
	    }
	}
	return flag;
}
//a^b%m
int get_power(int b,int n,int m)
{
//	cout<<b<<" "<<n<<" "<<m<<" ";
//	计算a^b%m 快速幂
	int a=1;
	while (n!=0)
	{
		int flag=n&1;
		n>>=1;
		a=a*(flag?b:1)%m;
		b=b*b%m;
	}
//	cout<<a<<endl;
	return a;
}
//求因数
vector<int> get_factor(int x)
{
	vector<int> v;
	for(int i=1;i<=x;i++)
	{
		if(x%i==0)
		{
			v.push_back(i);
		}
	}
	return v;
}
//求素因数
vector<int> get_prime_factor(int x)
{
	vector<int> v;
	for(int i=2;i<=x;i++)
	{
		if(x%i==0)
		{
			v.push_back(i);
			while(x%i==0)
			{
				x/=i;
			}
		}
	}
	return v;
}
//求指数
int get_exponent(vector<int> factors,int a,int m)
{
	for(vector<int>::iterator it=factors.begin();it!=factors.end();it++)
	{
		if(get_power(a,*it,m)==1)
		{
			return *it;
		}
	}
}
//求一个原根
int get_primordial_root(vector<int> prime_factors,int m)
{
	for(vector<int>::iterator it=prime_factors.begin();it!=prime_factors.end();it++)
	{
		*it=euler_value/(*it);
	}
	int a=2;
	int flag;
	while(1)
	{
		flag=0;
		for(vector<int>::iterator it=prime_factors.begin();it!=prime_factors.end();it++)
		{
			if(get_power(a,*it,m)==1)
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			return a;
		}
		a++;
	}
}
//求简化剩余系
vector<int> get_remains()
{
	vector<int> remains;
	for (int i = 1;i<euler_value; i++)
    {
        if(gcd(i,euler_value)==1)
        {
        	remains.push_back(i);
		}
    }
    return remains;
}
//求原根
vector<int> get_primordial_roots(vector<int> remains,int m,int primordial_root)
{
	vector<int> primordial_roots;
	for(vector<int>::iterator it=remains.begin();it!=remains.end();it++)
	{
		primordial_roots.push_back(get_power(primordial_root,*it,m));
	}
	return primordial_roots;
}
void check(int x)
{
	vector<int> factors=get_factor(euler_value);
//	再用a^{p-1}=1计算最小的：指数
	exponent=get_exponent(factors,x,p);
	if(exponent==p-1)
	{
		cout<<"success"<<endl;
	}
	else
	{
		cout<<"failed"<<endl;
	}
}
void show()
{
	cout<<"原根：";
	for(vector<int>::iterator it=primordial_roots.begin();it!=primordial_roots.end();it++)
	{
		write(*it);
//		check(*it);
		putchar(' ');
	}
	cout<<endl;
}
signed main()
{
//	输入a,p  2200013
 	a=2,p=22000131;
// 	判断是否为奇素数
//	if(!judge_valid(p)||!(p%2))
//	{
//		cout<<"不合法";
//		return 0;
//	}
//	求φ(p)=p-1
	euler_value=p-1;
//	求p-1的因数
	vector<int> factors=get_factor(euler_value);
//	再用a^{p-1}=1计算最小的：指数
	exponent=get_exponent(factors,a,p);
	cout<<"指数："<< exponent<<endl;
//	求p-1的素因数
	vector<int> prime_factors=get_prime_factor(euler_value);
	cout<<"素因数：";
	for(vector<int>::iterator it=prime_factors.begin();it!=prime_factors.end();it++)
	{
		cout<<*it<<" ";
	}
	cout<<endl;
//	求一个原根
	single_primordial_root=get_primordial_root(prime_factors,p);
	cout<<"原根："<<single_primordial_root<<endl;
//	求p-1的简化剩余系
	vector<int> remains=get_remains();
//	cout<<"简化剩余系：";
//	for(vector<int>::iterator it=remains.begin();it!=remains.end();it++)
//	{
//		cout<<*it<<" ";
//	}
//	cout<<endl;
//	利用简化剩余系求原根
	primordial_roots=get_primordial_roots(remains,p,single_primordial_root);
//	show();
	return 0;
}