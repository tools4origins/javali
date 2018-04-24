# Java LI

Adding an index to a sorted list of values is equivalent to storing the 
cumulative distribution function (CDF) of those values.

Instead of using a custom data structure like a B-Tree, 
Tim Kraska, Alex Beutel, Ed H. Chi, Jeffrey Dean and Neoklis Polyzotis propose in
[The Case for Learned Index Structures](https://arxiv.org/abs/1712.01208) to use a
model to overfit this CDF and then use the model as an index. 

This repository contains some experiments in Java with CDF, indexes amd learned index structures.
