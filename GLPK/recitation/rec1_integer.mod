set FOODS;
set CONSTRAINTS;

var quantity{i in FOODS} >=0, integer;

param sodium_content{i in FOODS};
param bound{j in CONSTRAINTS};
param coefficients{j in CONSTRAINTS, i in FOODS};

minimize sodium_intake: sum{i in FOODS} sodium_content[i]*quantity[i];

s.t. constraints{j in CONSTRAINTS}: sum{i in FOODS} coefficients[j,i]*quantity[i] <= bound[j];

data;

set FOODS := B P S C;
set CONSTRAINTS := Prot Fat Carb Cal Cost;

param sodium_content := B 1500 P 600 S 1060 C 180;

param bound := Prot -58 Fat 65 Carb 200 Cal 2000 Cost 30;

param coefficients:
        B   P    S   C :=
Prot  -25  -9  -20  -2
Fat    42   9   37   2
Carb   57  26   88  25
Cal   700 210  780 160
Cost    7   4    6   2;  