import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import time



#3-1是根号x
#3-2是sinx
#3-3是cosx
address='E:/3-1.xls'
X = []
Y = []

#读入数据
#绘图

def excel_one_line_to_list():
    df = pd.read_excel(address, usecols=[0],
                       names=None)  # 读取第一列，不要列名
    df_li = df.values.tolist()
    df1 = pd.read_excel(address, usecols=[1],
                       names=None)  # 读取第一列，不要列名
    df_li1 = df1.values.tolist()

    for s_li in df_li:
        X.append(s_li[0])
    for s_li in df_li1:
        Y.append(s_li[0])
    return X,Y

#牛顿插值
def newton_interpolation(X,Y,init):
    sum=Y[0]
    temp=np.zeros((len(X),len(X)))
    #将第一行赋值
    for i in range(0,len(X)):
        temp[i,0]=Y[i]
    temp_sum=1.0
    for i in range(1,len(X)):
        #x的多项式
        temp_sum=temp_sum*(init-X[i-1])
        #计算均差
        for j in range(i,len(X)):
            temp[j,i]=(temp[j,i-1]-temp[j-1,i-1])/(X[j]-X[j-i])
        sum+=temp_sum*temp[i,i]
    return sum

#拉格朗日插值
def lagrange_interpolation(X,Y,init):
    sum=0.0
    for i in range(len(X)):
        temp=Y[i]
        for j in range(len(X)):
            if(j!=i):
                temp=temp*((init-X[j])/(X[i]-X[j]))
        sum=sum+temp
    return sum


def showpicture():
    lxi = X.index(min(X))
    lxa = X.index(max(X))
    lyi = Y.index(min(Y))
    lya = Y.index(max(Y))
    # my_x_ticks = np.arange(X[lxi], X[lxa], 0.03)
    # my_y_ticks = np.arange(Y[lyi], Y[lya], 0.01)
    # plt.figure(figsize=(7, 7))
    # plt.autoscale()
    plt.xlim((X[lxi], X[lxa]))
    plt.ylim((Y[lyi], Y[lya]))
    # plt.axis(xin, xax, yin, yax)
    plt.locator_params('y', nbins=30)
    plt.plot(X, Y, 's', label="original values")  # 蓝点表示原来的值
    plt.xlabel('x', fontsize=12)
    plt.ylabel('y', fontsize=12)
    plt.legend(loc=4)  # 指定legend的位置右下角
    # plt.xticks(my_x_ticks)
    plt.show()
def main():
    (X,Y) = excel_one_line_to_list()
    #X=[float(i) for i in (input("请输入X的对应值：").split())]
    #Y=[float(i) for i in (input("请输入Y的对应值：").split())]
    N = int(input("请输入要执行插值的次数："))
    for i in range(N) :
        init=float(input("请输入要计算的x："))
        print("选择插值方法：\n\t1.拉格朗日插值\n\t2.牛顿插值")
        choice=int(input("请选择一种方法："))
        #在最小值至最大值区间取1000点
        X_temp=np.linspace(np.min(X),np.max(X),1000,endpoint=True)
        Y_temp=[]
        if(choice==1):
            start = time.perf_counter()
            result = lagrange_interpolation(X, Y, init)
            end = time.perf_counter()
            for x in X_temp:
                Y_temp.append(lagrange_interpolation(X, Y, x))
            plt.plot(X_temp, Y_temp, 'r', label='interpolation values')  # 插值曲线
            lxi = X.index(min(X))
            lxa = X.index(max(X))
            lyi = Y.index(min(Y))
            lya = Y.index(max(Y))
            plt.scatter(init, result, marker='x')
            plt.vlines(init, Y[lyi], result, colors="c", linestyles="dashed")
            plt.hlines(result, X[lxi], init, colors="c", linestyles="dashed")
            # 显示坐标点坐标值
            plt.text(init, result, (float('%.2f' % init), result), ha='left', va='top', fontsize=11)
            plt.title("lagrange_interpolation")
            showpicture()
            print("lagrange run time:", str(end - start))
            print("f(%f)的近似值为%f" % (init, result))
        else:
            start1 = time.perf_counter()
            result=newton_interpolation(X,Y,init)
            end1 = time.perf_counter()
            for x in X_temp:
                Y_temp.append(newton_interpolation(X,Y,x))
            plt.title("newton_interpolation",fontsize=13)
            plt.plot(X_temp, Y_temp, 'r', label='interpolation values')  # 插值曲线
            lxi = X.index(min(X))
            lyi = Y.index(min(Y))
            plt.scatter(init, result, marker='x')
            plt.vlines(init, Y[lyi], result, colors="c", linestyles="dashed")
            plt.hlines(result, X[lxi], init, colors="c", linestyles="dashed")
            # 显示坐标点坐标值
            plt.text(init, result, (float('%.2f' % init), result), ha='left', va='top', fontsize=11)
            showpicture()
            print("newton run time:", str(end1 - start1))
            print("f(%f)的近似值为%f" % (init, result))

if __name__=='__main__':
    main()

