/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "E:/workspace_cse260/lab1/binaryOutMod.vhd";
extern char *IEEE_P_3620187407;
extern char *WORK_P_3505653787;
extern char *IEEE_P_2592010699;

unsigned char ieee_p_2592010699_sub_1744673427_503743352(char *, char *, unsigned int , unsigned int );
char *ieee_p_3620187407_sub_436279890_3965413181(char *, char *, char *, char *, int );
int work_p_3505653787_sub_2696589364_3773489021(char *, char *, char *);


static void work_a_3467311171_0521025630_p_0(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    int t4;
    int t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;

LAB0:    xsi_set_current_line(46, ng0);

LAB3:    t1 = (t0 + 1992U);
    t2 = *((char **)t1);
    t1 = (t0 + 2928U);
    t3 = *((char **)t1);
    t4 = *((int *)t3);
    t5 = (t4 - 6);
    t6 = (19 - t5);
    t7 = (t6 * 1U);
    t8 = (0 + t7);
    t1 = (t2 + t8);
    t9 = (t0 + 5368);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    memcpy(t13, t1, 15U);
    xsi_driver_first_trans_fast(t9);

LAB2:    t14 = (t0 + 5224);
    *((int *)t14) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_3467311171_0521025630_p_1(char *t0)
{
    char t11[16];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    unsigned int t5;
    int t6;
    unsigned int t7;
    unsigned int t8;
    char *t9;
    unsigned int t10;
    char *t12;
    char *t13;
    int t14;
    unsigned int t15;
    unsigned char t16;
    char *t17;
    char *t18;
    char *t19;
    char *t20;
    char *t21;
    char *t22;
    char *t23;
    char *t24;
    char *t25;
    char *t26;

LAB0:    xsi_set_current_line(47, ng0);
    t1 = (t0 + 2152U);
    t2 = *((char **)t1);
    t1 = (t0 + 8012U);
    t3 = xsi_get_transient_memory(15U);
    memset(t3, 0, 15U);
    t4 = t3;
    if (-1 == -1)
        goto LAB5;

LAB6:    t5 = 0;

LAB7:    t6 = (t5 - 14);
    t7 = (t6 * -1);
    t8 = (1U * t7);
    t9 = (t4 + t8);
    t10 = (1U * 15U);
    memset(t9, (unsigned char)2, t10);
    t12 = (t11 + 0U);
    t13 = (t12 + 0U);
    *((int *)t13) = 0;
    t13 = (t12 + 4U);
    *((int *)t13) = 14;
    t13 = (t12 + 8U);
    *((int *)t13) = 1;
    t14 = (14 - 0);
    t15 = (t14 * 1);
    t15 = (t15 + 1);
    t13 = (t12 + 12U);
    *((unsigned int *)t13) = t15;
    t16 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t2, t1, t3, t11);
    if (t16 != 0)
        goto LAB3;

LAB4:
LAB8:    t21 = (t0 + 5432);
    t22 = (t21 + 56U);
    t23 = *((char **)t22);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    *((unsigned char *)t25) = (unsigned char)2;
    xsi_driver_first_trans_fast(t21);

LAB2:    t26 = (t0 + 5240);
    *((int *)t26) = 1;

LAB1:    return;
LAB3:    t13 = (t0 + 5432);
    t17 = (t13 + 56U);
    t18 = *((char **)t17);
    t19 = (t18 + 56U);
    t20 = *((char **)t19);
    *((unsigned char *)t20) = (unsigned char)3;
    xsi_driver_first_trans_fast(t13);
    goto LAB2;

LAB5:    t5 = 14;
    goto LAB7;

LAB9:    goto LAB2;

}

static void work_a_3467311171_0521025630_p_2(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    int t4;
    int t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;

LAB0:    xsi_set_current_line(48, ng0);

LAB3:    t1 = (t0 + 1992U);
    t2 = *((char **)t1);
    t1 = (t0 + 2928U);
    t3 = *((char **)t1);
    t4 = *((int *)t3);
    t5 = (t4 - 1);
    t6 = (19 - t5);
    t7 = (t6 * 1U);
    t8 = (0 + t7);
    t1 = (t2 + t8);
    t9 = (t0 + 5496);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    memcpy(t13, t1, 5U);
    xsi_driver_first_trans_fast(t9);

LAB2:    t14 = (t0 + 5256);
    *((int *)t14) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_3467311171_0521025630_p_3(char *t0)
{
    char t20[16];
    char t50[16];
    unsigned char t1;
    unsigned char t2;
    char *t3;
    char *t4;
    int t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    unsigned char t9;
    unsigned char t10;
    char *t11;
    char *t12;
    char *t13;
    int t14;
    int t15;
    char *t16;
    unsigned int t17;
    unsigned int t18;
    unsigned int t19;
    char *t21;
    char *t22;
    int t23;
    unsigned int t24;
    int t25;
    int t26;
    int t27;
    unsigned int t28;
    unsigned int t29;
    unsigned char t30;
    unsigned char t31;
    unsigned char t32;
    char *t33;
    char *t34;
    int t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned char t39;
    unsigned char t40;
    char *t41;
    char *t42;
    char *t43;
    int t44;
    int t45;
    char *t46;
    unsigned int t47;
    unsigned int t48;
    unsigned int t49;
    char *t51;
    char *t52;
    int t53;
    unsigned int t54;
    int t55;
    int t56;
    int t57;
    unsigned int t58;
    unsigned int t59;
    unsigned char t60;
    unsigned char t61;
    char *t62;
    char *t64;
    char *t65;
    char *t66;
    char *t67;
    char *t68;
    char *t69;
    char *t71;
    char *t72;
    char *t73;
    char *t74;
    char *t75;
    char *t76;

LAB0:    xsi_set_current_line(50, ng0);
    t3 = (t0 + 2472U);
    t4 = *((char **)t3);
    t5 = (4 - 4);
    t6 = (t5 * -1);
    t7 = (1U * t6);
    t8 = (0 + t7);
    t3 = (t4 + t8);
    t9 = *((unsigned char *)t3);
    t10 = (t9 == (unsigned char)2);
    if (t10 == 1)
        goto LAB8;

LAB9:    t2 = (unsigned char)0;

LAB10:    if (t2 == 1)
        goto LAB5;

LAB6:    t33 = (t0 + 2472U);
    t34 = *((char **)t33);
    t35 = (4 - 4);
    t36 = (t35 * -1);
    t37 = (1U * t36);
    t38 = (0 + t37);
    t33 = (t34 + t38);
    t39 = *((unsigned char *)t33);
    t40 = (t39 == (unsigned char)3);
    if (t40 == 1)
        goto LAB11;

LAB12:    t32 = (unsigned char)0;

LAB13:    t1 = t32;

LAB7:    if (t1 != 0)
        goto LAB3;

LAB4:
LAB14:    t69 = (t0 + 8165);
    t71 = (t0 + 5560);
    t72 = (t71 + 56U);
    t73 = *((char **)t72);
    t74 = (t73 + 56U);
    t75 = *((char **)t74);
    memcpy(t75, t69, 8U);
    xsi_driver_first_trans_fast(t71);

LAB2:    t76 = (t0 + 5272);
    *((int *)t76) = 1;

LAB1:    return;
LAB3:    t62 = (t0 + 8157);
    t64 = (t0 + 5560);
    t65 = (t64 + 56U);
    t66 = *((char **)t65);
    t67 = (t66 + 56U);
    t68 = *((char **)t67);
    memcpy(t68, t62, 8U);
    xsi_driver_first_trans_fast(t64);
    goto LAB2;

LAB5:    t1 = (unsigned char)1;
    goto LAB7;

LAB8:    t11 = (t0 + 1512U);
    t12 = *((char **)t11);
    t11 = ((WORK_P_3505653787) + 1168U);
    t13 = *((char **)t11);
    t14 = *((int *)t13);
    t15 = (t14 - 1);
    t11 = (t0 + 2472U);
    t16 = *((char **)t11);
    t17 = (4 - 3);
    t18 = (t17 * 1U);
    t19 = (0 + t18);
    t11 = (t16 + t19);
    t21 = (t20 + 0U);
    t22 = (t21 + 0U);
    *((int *)t22) = 3;
    t22 = (t21 + 4U);
    *((int *)t22) = 0;
    t22 = (t21 + 8U);
    *((int *)t22) = -1;
    t23 = (0 - 3);
    t24 = (t23 * -1);
    t24 = (t24 + 1);
    t22 = (t21 + 12U);
    *((unsigned int *)t22) = t24;
    t25 = work_p_3505653787_sub_2696589364_3773489021(WORK_P_3505653787, t11, t20);
    t26 = (t15 - t25);
    t27 = (t26 - 15);
    t24 = (t27 * -1);
    xsi_vhdl_check_range_of_index(15, 0, -1, t26);
    t28 = (1U * t24);
    t29 = (0 + t28);
    t22 = (t12 + t29);
    t30 = *((unsigned char *)t22);
    t31 = (t30 == (unsigned char)2);
    t2 = t31;
    goto LAB10;

LAB11:    t41 = (t0 + 1672U);
    t42 = *((char **)t41);
    t41 = ((WORK_P_3505653787) + 1168U);
    t43 = *((char **)t41);
    t44 = *((int *)t43);
    t45 = (t44 - 1);
    t41 = (t0 + 2472U);
    t46 = *((char **)t41);
    t47 = (4 - 3);
    t48 = (t47 * 1U);
    t49 = (0 + t48);
    t41 = (t46 + t49);
    t51 = (t50 + 0U);
    t52 = (t51 + 0U);
    *((int *)t52) = 3;
    t52 = (t51 + 4U);
    *((int *)t52) = 0;
    t52 = (t51 + 8U);
    *((int *)t52) = -1;
    t53 = (0 - 3);
    t54 = (t53 * -1);
    t54 = (t54 + 1);
    t52 = (t51 + 12U);
    *((unsigned int *)t52) = t54;
    t55 = work_p_3505653787_sub_2696589364_3773489021(WORK_P_3505653787, t41, t50);
    t56 = (t45 - t55);
    t57 = (t56 - 15);
    t54 = (t57 * -1);
    xsi_vhdl_check_range_of_index(15, 0, -1, t56);
    t58 = (1U * t54);
    t59 = (0 + t58);
    t52 = (t42 + t59);
    t60 = *((unsigned char *)t52);
    t61 = (t60 == (unsigned char)2);
    t32 = t61;
    goto LAB13;

LAB15:    goto LAB2;

}

static void work_a_3467311171_0521025630_p_4(char *t0)
{
    char t3[16];
    char *t1;
    unsigned char t2;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    unsigned int t8;
    unsigned int t9;
    unsigned char t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;

LAB0:    xsi_set_current_line(57, ng0);
    t1 = (t0 + 992U);
    t2 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t2 != 0)
        goto LAB2;

LAB4:
LAB3:    t1 = (t0 + 5288);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(58, ng0);
    t4 = (t0 + 1992U);
    t5 = *((char **)t4);
    t4 = (t0 + 7996U);
    t6 = ieee_p_3620187407_sub_436279890_3965413181(IEEE_P_3620187407, t3, t5, t4, 1);
    t7 = (t3 + 12U);
    t8 = *((unsigned int *)t7);
    t9 = (1U * t8);
    t10 = (20U != t9);
    if (t10 == 1)
        goto LAB5;

LAB6:    t11 = (t0 + 5624);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    t14 = (t13 + 56U);
    t15 = *((char **)t14);
    memcpy(t15, t6, 20U);
    xsi_driver_first_trans_fast(t11);
    xsi_set_current_line(59, ng0);
    t1 = (t0 + 1192U);
    t4 = *((char **)t1);
    t2 = *((unsigned char *)t4);
    t10 = (t2 == (unsigned char)3);
    if (t10 != 0)
        goto LAB7;

LAB9:
LAB8:    goto LAB3;

LAB5:    xsi_size_not_matching(20U, t9, 0);
    goto LAB6;

LAB7:    xsi_set_current_line(60, ng0);
    t1 = xsi_get_transient_memory(20U);
    memset(t1, 0, 20U);
    t5 = t1;
    memset(t5, (unsigned char)2, 20U);
    t6 = (t0 + 5624);
    t7 = (t6 + 56U);
    t11 = *((char **)t7);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    memcpy(t13, t1, 20U);
    xsi_driver_first_trans_fast(t6);
    goto LAB8;

}


extern void work_a_3467311171_0521025630_init()
{
	static char *pe[] = {(void *)work_a_3467311171_0521025630_p_0,(void *)work_a_3467311171_0521025630_p_1,(void *)work_a_3467311171_0521025630_p_2,(void *)work_a_3467311171_0521025630_p_3,(void *)work_a_3467311171_0521025630_p_4};
	xsi_register_didat("work_a_3467311171_0521025630", "isim/top_isim_beh.exe.sim/work/a_3467311171_0521025630.didat");
	xsi_register_executes(pe);
}
