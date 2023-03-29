import csv
from time import time
import datetime
from sklearn import metrics
from sklearn.model_selection import train_test_split                      # 数据集分割
from sklearn.svm import SVC
from sklearn.metrics import roc_auc_score as AUC
from sklearn.metrics import roc_curve
from sklearn import tree                                                  # 决策树和SVM
from sklearn.neighbors import KNeighborsClassifier                        # KNN
from sklearn.preprocessing import StandardScaler, MinMaxScaler, Normalizer  # 数据标准化, 归一化, 正则化
from sklearn.ensemble import RandomForestClassifier                       # 随机森林
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import collections
from imblearn.over_sampling import SMOTE
from sklearn.metrics import matthews_corrcoef
import warnings
warnings.filterwarnings('ignore')


# # 决策树：没用
# # 存放训练集和测试集
# x = []
# y = []
# xx = []
# yy = []
# # 1548 * 8001
# # 读取csv文件
# with open('train_dataset1.csv', 'r') as f:
#     reader = csv.reader(f)
#     for i in reader:
#         # print(i[8000])
#         x.append(i[0:8000])
#         y.append(i[8000])
#
# with open('test_dataset1.csv', 'r') as g:
#     reader = csv.reader(g)
#     for i in reader:
#         # print(i[8000])
#         xx.append(i[0:8000])
#         yy.append(i[8000])
#
# # 将数据集中的2替换为1.
# def func_1(x):
#     lists = x
#     lists2 = []
#     for a in lists:
#         for b in a:
#             lists2.append(b)
#     e = ['0' if (i != '2' and i != '1' and i != '0') else i for i in lists2]
#     return e
#
# x = func_1(x)
# xx = func_1(xx)
# x_train, y_train = np.array(x).reshape(-1, 8000), np.array(y)
# x_test, y_test = np.array(xx).reshape(-1, 8000), np.array(yy)
#
# print(len(y_test))
# print(len(y_train))
#
# std = StandardScaler()
# # 对训练集和测试集的特征值进行标准化
# x_train = std.fit_transform(x_train)
# x_test = std.fit_transform(x_test)
#
# # 划分测试集和训练集
# # x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.1, random_state=1)
# time0 = time()
# print(datetime.datetime.fromtimestamp(time()-time0).strftime("%M:%S:%f"))    # 得到计算时间
# clf = tree.DecisionTreeClassifier(criterion="entropy", random_state=30, splitter="random")
# print(datetime.datetime.fromtimestamp(time()-time0).strftime("%M:%S:%f"))    # 得到计算时间
# clf = clf.fit(x_train, y_train)
# print(datetime.datetime.fromtimestamp(time()-time0).strftime("%M:%S:%f"))    # 得到计算时间
# score = clf.score(x_test, y_test)
# print("DecisionTree :", score)
# clf_y_predict = clf.predict_proba(x_test)[:, 1]
# print(clf_y_predict)
# # 画图部分
# fpr, tpr, threshold = metrics.roc_curve(list(map(float, y_test)), list(map(float, clf_y_predict)))
# roc_auc = metrics.auc(fpr, tpr)
# plt.figure(figsize=(6, 6))
# plt.title('Validation ROC')
# plt.plot(fpr, tpr, 'b', label='Val AUC = %0.3f' % roc_auc)
# plt.legend(loc='lower right')
# plt.plot([0, 1], [0, 1], 'r--')
# plt.xlim([0, 1])
# plt.ylim([0, 1])
# plt.ylabel('True Positive Rate')
# plt.xlabel('False Positive Rate')
# plt.show()


# svm dataset3  auc:0.88
# 存放训练集和测试集
x = []
y = []
xx = []
yy = []
# 读取csv文件
with open('train_dataset2.csv', 'r') as f:
    reader = csv.reader(f)
    for i in reader:
        # print(i[8000])
        x.append(i[0:8000])
        y.append(i[8000])

with open('test_dataset2.csv', 'r') as g:
    reader = csv.reader(g)
    for i in reader:
        # print(i[8000])
        xx.append(i[0:8000])
        yy.append(i[8000])

# 将数据集中的非012替换为0.
def func_1(x):
    lists = x
    lists2 = []
    for a in lists:
        for b in a:
            lists2.append(b)
    e = ['0' if (i != '2' and i != '1' and i != '0') else i for i in lists2]
    return e
# 特征列表中str转int
def func_2(x):
    lists = x
    lists2 = []
    for a in lists:
        for b in a:
            lists2.append(b)
    # e = ['0' if (i != '2' and i != '1' and i != '0') else i for i in lists2]
    b = [int(i)for i in lists2]
    e = np.array(b).reshape(-1, 8000)
    return e

x = func_1(x)
xx = func_1(xx)
x = func_2(x)
xx = func_2(xx)

y = [int(i)for i in y]  # str转int
yy = [int(i)for i in yy]
x_train, y_train = np.array(x).reshape(-1, 8000), np.array(y)
x_test, y_test = np.array(xx).reshape(-1, 8000), np.array(yy)
std = StandardScaler()
# 对训练集和测试集的特征值进行标准化
x_train = std.fit_transform(x_train)
x_test = std.fit_transform(x_test)

Xtrain, Ytrain = x_train, y_train
Xtest, Ytest = x_test, y_test
print('ytest:', Ytest)
# Kernel = ["rbf"]
# for kernel in Kernel:
#     time0 = time()
#     clf = SVC(kernel=kernel, gamma="auto", degree=1, cache_size=7000).fit(Xtrain, Ytrain)  # 允许使用多大的内存MB 默认200
#     print("The accuracy under kernel %s is %f" % (kernel, clf.score(Xtest, Ytest)))
#     print(datetime.datetime.fromtimestamp(time()-time0).strftime("%M:%S:%f"))    # 得到计算时间

time0 = time()
clf_proba = SVC(kernel="rbf", gamma="auto", degree=1, cache_size=7000, C=0.1, probability=True).fit(Xtrain, Ytrain)
print(datetime.datetime.fromtimestamp(time()-time0).strftime("%M:%S:%f"))    # 得到计算时间
print('Ytest:', Ytest)
fpr, tpr, thresholds = roc_curve(Ytest, clf_proba.decision_function(Xtest), pos_label=1)
print('pass')
print(fpr.shape)        # (45,))
print(tpr.shape)        # (45,))
# 此时的threshold就不是一个概率值，而是距离值中的阈值了，所以它可以大于1，也可以为负
print(thresholds.shape)    # (45,))

area = AUC(Ytest, clf_proba.decision_function(Xtest))
print(area)     # 0.9696400000000001

# 画出ROC曲线
plt.figure()
plt.plot(fpr, tpr, color='blue'
         , label='ROC curve (area = %0.2f)' % area)
plt.plot([0, 1], [0, 1], color='red', linestyle='--')
# 为了让曲线不黏在图的边缘
plt.xlim([-0.05, 1.05])
plt.ylim([-0.05, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Validation ROC')
plt.legend(loc="lower right")
plt.show()

mcc = matthews_corrcoef(y_test, clf_proba.predict(Xtest))   # MCC指标
print('matthews_corrcoef:', mcc)



# # knn:dataset2  auc:0.76
# # 存放训练集和测试集
# x = []
# y = []
# xx = []
# yy = []
# # 读取csv文件
# with open('train_dataset2.csv', 'r') as f:
#     reader = csv.reader(f)
#     for i in reader:
#         # print(i[8000])
#         x.append(i[0:8000])
#         y.append(i[8000])
#
# with open('test_dataset2.csv', 'r') as g:
#     reader = csv.reader(g)
#     for i in reader:
#         # print(i[8000])
#         xx.append(i[0:8000])
#         yy.append(i[8000])
#
# # 将数据集中的非012替换为0.
# def func_1(x):
#     lists = x
#     lists2 = []
#     for a in lists:
#         for b in a:
#             lists2.append(b)
#     e = ['0' if (i != '2' and i != '1' and i != '0') else i for i in lists2]
#     return e
# # 列表中str转int
# def func_2(x):
#     lists = x
#     lists2 = []
#     for a in lists:
#         for b in a:
#             lists2.append(b)
#     # e = ['0' if (i != '2' and i != '1' and i != '0') else i for i in lists2]
#     b = [int(i)for i in lists2]
#     e = np.array(b).reshape(-1, 8000)
#     return e
#
# x = func_1(x)
# xx = func_1(xx)
#
# x_train, y_train = np.array(x).reshape(-1, 8000), np.array(y)
# x_test, y_test = np.array(xx).reshape(-1, 8000), np.array(yy)
#
# print(x_train, y_train)
# print(x_test, y_test)
# print(len(y_test))
# print(len(y_train))
# std = StandardScaler()
# # 对训练集和测试集的特征值进行标准化
# x_train = std.fit_transform(x_train)
# x_test = std.fit_transform(x_test)
#
# # x_train = std.fit_transform(x_train)
# # x_test = std.fit_transform(x_test)
#
# # def searchBestPar():
# #     bestScore = 0
# #     bestK = -1
# #     bestWeight = ""
# #     # # weight==uniform时
# #     # for k in range(1, 10):
# #     #     clf = KNeighborsClassifier(n_neighbors=k, weights="uniform")
# #     #     clf.fit(x_train, y_train)
# #     #     scor = clf.score(x_test, y_test)
# #     #     if scor > bestScore:
# #     #         bestScore = scor
# #     #         bestK = k
# #     #         bestWeight = "uniform"
# #
# #     # weight==distance时
# #     for k in range(3, 25):
# #         for p in range(2, 3):
# #             knn = KNeighborsClassifier(n_neighbors=k, weights="distance", p=p)
# #             knn.fit(x_train, y_train)
# #             scor = knn.score(x_test, y_test)
# #             if scor > bestScore:
# #                 bestScore = scor
# #                 bestK = k
# #                 bestWeight = "distance"
# #                 print("the best n_neighbors", bestK)
# #                 print("the best weights", bestWeight)
# #                 print("the best p", p)
# #
# #             knn_y_predict = knn.predict_proba(x_test)[:, 1]
# #             # knn_r_test = knn.score(x_test, y_test)
# #             print("knn算法训练上准确率：", scor)
# #             # 画图部分
# #             fpr, tpr, threshold = metrics.roc_curve(list(map(float, y_test)), list(map(float, knn_y_predict)))
# #             roc_auc = metrics.auc(fpr, tpr)
# #             print('roc_auc:', roc_auc)
# #             plt.figure(figsize=(6, 6))
# #             plt.title('Validation ROC')
# #             plt.plot(fpr, tpr, 'b', label='Val AUC = %0.3f' % roc_auc)
# #             plt.legend(loc='lower right')
# #             plt.plot([0, 1], [0, 1], 'r--')
# #             plt.xlim([0, 1])
# #             plt.ylim([0, 1])
# #             plt.ylabel('True Positive Rate')
# #             plt.xlabel('False Positive Rate')
# #             plt.show()
# #
# #     print("the best n_neighbors", bestK)
# #     print("the best weights", bestWeight)
# #     print("the best p", p)
# #
# # searchBestPar()
# knn = KNeighborsClassifier(n_neighbors=8, algorithm='kd_tree', weights='distance', p=2)
# knn.fit(x_train, y_train)
#
# knn_y_predict = knn.predict_proba(x_test)[:, 1]
# knn_r_test = knn.score(x_test, y_test)
# print("knn算法训练上准确率：", knn_r_test)
#
# # 画图部分
# fpr, tpr, threshold = metrics.roc_curve(list(map(float, y_test)), list(map(float, knn_y_predict)))
# roc_auc = metrics.auc(fpr, tpr)
# plt.figure(figsize=(6, 6))
# plt.title('Validation ROC')
# plt.plot(fpr, tpr, 'b', label='Val AUC = %0.3f' % roc_auc)
# plt.legend(loc='lower right')
# plt.plot([0, 1], [0, 1], 'r--')
# plt.xlim([0, 1])
# plt.ylim([0, 1])
# plt.ylabel('True Positive Rate')
# plt.xlabel('False Positive Rate')
# plt.show()
#
# mcc = matthews_corrcoef(y_test, knn.predict(x_test))   # MCC指标
# print('matthews_corrcoef:', mcc)
