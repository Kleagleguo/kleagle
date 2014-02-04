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
unsigned char ieee_p_3620187407_sub_4060537613_3965413181(char *, char *, char *, char *, char *);
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

LAB0:    xsi_set_current_line(57, ng0);

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

LAB0:    xsi_set_current_line(58, ng0);
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

LAB0:    xsi_set_current_line(59, ng0);

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
    char t9[16];
    char t39[16];
    char t69[16];
    unsigned char t1;
    char *t2;
    char *t3;
    unsigned char t4;
    unsigned char t5;
    char *t6;
    char *t7;
    char *t10;
    char *t11;
    int t12;
    unsigned int t13;
    unsigned char t14;
    char *t16;
    char *t17;
    char *t18;
    char *t19;
    char *t20;
    unsigned char t21;
    unsigned char t22;
    char *t23;
    char *t24;
    int t25;
    unsigned int t26;
    unsigned int t27;
    unsigned char t28;
    unsigned char t29;
    char *t30;
    char *t31;
    char *t32;
    int t33;
    int t34;
    char *t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    char *t40;
    char *t41;
    int t42;
    unsigned int t43;
    int t44;
    int t45;
    int t46;
    unsigned int t47;
    unsigned int t48;
    unsigned char t49;
    unsigned char t50;
    unsigned char t51;
    char *t52;
    char *t53;
    int t54;
    unsigned int t55;
    unsigned int t56;
    unsigned int t57;
    unsigned char t58;
    unsigned char t59;
    char *t60;
    char *t61;
    char *t62;
    int t63;
    int t64;
    char *t65;
    unsigned int t66;
    unsigned int t67;
    unsigned int t68;
    char *t70;
    char *t71;
    int t72;
    unsigned int t73;
    int t74;
    int t75;
    int t76;
    unsigned int t77;
    unsigned int t78;
    unsigned char t79;
    unsigned char t80;
    char *t81;
    char *t83;
    char *t84;
    char *t85;
    char *t86;
    char *t87;
    char *t88;
    char *t90;
    char *t91;
    char *t92;
    char *t93;
    char *t94;
    char *t95;

LAB0:    xsi_set_current_line(61, ng0);
    t2 = (t0 + 1352U);
    t3 = *((char **)t2);
    t4 = *((unsigned char *)t3);
    t5 = (t4 == (unsigned char)3);
    if (t5 == 1)
        goto LAB5;

LAB6:    t1 = (unsigned char)0;

LAB7:    if (t1 != 0)
        goto LAB3;

LAB4:    t23 = (t0 + 2472U);
    t24 = *((char **)t23);
    t25 = (4 - 4);
    t13 = (t25 * -1);
    t26 = (1U * t13);
    t27 = (0 + t26);
    t23 = (t24 + t27);
    t28 = *((unsigned char *)t23);
    t29 = (t28 == (unsigned char)2);
    if (t29 == 1)
        goto LAB13;

LAB14:    t22 = (unsigned char)0;

LAB15:    if (t22 == 1)
        goto LAB10;

LAB11:    t52 = (t0 + 2472U);
    t53 = *((char **)t52);
    t54 = (4 - 4);
    t55 = (t54 * -1);
    t56 = (1U * t55);
    t57 = (0 + t56);
    t52 = (t53 + t57);
    t58 = *((unsigned char *)t52);
    t59 = (t58 == (unsigned char)3);
    if (t59 == 1)
        goto LAB16;

LAB17:    t51 = (unsigned char)0;

LAB18:    t21 = t51;

LAB12:    if (t21 != 0)
        goto LAB8;

LAB9:
LAB19:    t88 = (t0 + 8178);
    t90 = (t0 + 5560);
    t91 = (t90 + 56U);
    t92 = *((char **)t91);
    t93 = (t92 + 56U);
    t94 = *((char **)t93);
    memcpy(t94, t88, 8U);
    xsi_driver_first_trans_fast(t90);

LAB2:    t95 = (t0 + 5272);
    *((int *)t95) = 1;

LAB1:    return;
LAB3:    t11 = (t0 + 8162);
    t16 = (t0 + 5560);
    t17 = (t16 + 56U);
    t18 = *((char **)t17);
    t19 = (t18 + 56U);
    t20 = *((char **)t19);
    memcpy(t20, t11, 8U);
    xsi_driver_first_trans_fast(t16);
    goto LAB2;

LAB5:    t2 = (t0 + 2472U);
    t6 = *((char **)t2);
    t2 = (t0 + 8028U);
    t7 = (t0 + 8157);
    t10 = (t9 + 0U);
    t11 = (t10 + 0U);
    *((int *)t11) = 0;
    t11 = (t10 + 4U);
    *((int *)t11) = 4;
    t11 = (t10 + 8U);
    *((int *)t11) = 1;
    t12 = (4 - 0);
    t13 = (t12 * 1);
    t13 = (t13 + 1);
    t11 = (t10 + 12U);
    *((unsigned int *)t11) = t13;
    t14 = ieee_p_3620187407_sub_4060537613_3965413181(IEEE_P_3620187407, t6, t2, t7, t9);
    t1 = t14;
    goto LAB7;

LAB8:    t81 = (t0 + 8170);
    t83 = (t0 + 5560);
    t84 = (t83 + 56U);
    t85 = *((char **)t84);
    t86 = (t85 + 56U);
    t87 = *((char **)t86);
    memcpy(t87, t81, 8U);
    xsi_driver_first_trans_fast(t83);
    goto LAB2;

LAB10:    t21 = (unsigned char)1;
    goto LAB12;

LAB13:    t30 = (t0 + 1512U);
    t31 = *((char **)t30);
    t30 = ((WORK_P_3505653787) + 1168U);
    t32 = *((char **)t30);
    t33 = *((int *)t32);
    t34 = (t33 - 1);
    t30 = (t0 + 2472U);
    t35 = *((char **)t30);
    t36 = (4 - 3);
    t37 = (t36 * 1U);
    t38 = (0 + t37);
    t30 = (t35 + t38);
    t40 = (t39 + 0U);
    t41 = (t40 + 0U);
    *((int *)t41) = 3;
    t41 = (t40 + 4U);
    *((int *)t41) = 0;
    t41 = (t40 + 8U);
    *((int *)t41) = -1;
    t42 = (0 - 3);
    t43 = (t42 * -1);
    t43 = (t43 + 1);
    t41 = (t40 + 12U);
    *((unsigned int *)t41) = t43;
    t44 = work_p_3505653787_sub_2696589364_3773489021(WORK_P_3505653787, t30, t39);
    t45 = (t34 - t44);
    t46 = (t45 - 15);
    t43 = (t46 * -1);
    xsi_vhdl_check_range_of_index(15, 0, -1, t45);
    t47 = (1U * t43);
    t48 = (0 + t47);
    t41 = (t31 + t48);
    t49 = *((unsigned char *)t41);
    t50 = (t49 == (unsigned char)2);
    t22 = t50;
    goto LAB15;

LAB16:    t60 = (t0 + 1672U);
    t61 = *((char **)t60);
    t60 = ((WORK_P_3505653787) + 1168U);
    t62 = *((char **)t60);
    t63 = *((int *)t62);
    t64 = (t63 - 1);
    t60 = (t0 + 2472U);
    t65 = *((char **)t60);
    t66 = (4 - 3);
    t67 = (t66 * 1U);
    t68 = (0 + t67);
    t60 = (t65 + t68);
    t70 = (t69 + 0U);
    t71 = (t70 + 0U);
    *((int *)t71) = 3;
    t71 = (t70 + 4U);
    *((int *)t71) = 0;
    t71 = (t70 + 8U);
    *((int *)t71) = -1;
    t72 = (0 - 3);
    t73 = (t72 * -1);
    t73 = (t73 + 1);
    t71 = (t70 + 12U);
    *((unsigned int *)t71) = t73;
    t74 = work_p_3505653787_sub_2696589364_3773489021(WORK_P_3505653787, t60, t69);
    t75 = (t64 - t74);
    t76 = (t75 - 15);
    t73 = (t76 * -1);
    xsi_vhdl_check_range_of_index(15, 0, -1, t75);
    t77 = (1U * t73);
    t78 = (0 + t77);
    t71 = (t61 + t78);
    t79 = *((unsigned char *)t71);
    t80 = (t79 == (unsigned char)2);
    t51 = t80;
    goto LAB18;

LAB20:    goto LAB2;

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

LAB0:    xsi_set_current_line(70, ng0);
    t1 = (t0 + 992U);
    t2 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t2 != 0)
        goto LAB2;

LAB4:
LAB3:    t1 = (t0 + 5288);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(71, ng0);
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
    xsi_set_current_line(72, ng0);
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

LAB7:    xsi_set_current_line(73, ng0);
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
	xsi_register_didat("work_a_3467311171_0521025630", "isim/testTop_isim_beh.exe.sim/work/a_3467311171_0521025630.didat");
	xsi_register_executes(pe);
}
