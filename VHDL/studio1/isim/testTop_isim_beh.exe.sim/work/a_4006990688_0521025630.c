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
static const char *ng0 = "E:/workspace_cse260/studio1/debouncer.vhd";
extern char *IEEE_P_2592010699;
extern char *IEEE_P_3620187407;

unsigned char ieee_p_2592010699_sub_1744673427_503743352(char *, char *, unsigned int , unsigned int );
unsigned char ieee_p_3620187407_sub_4042748798_3965413181(char *, char *, char *, char *, char *);
char *ieee_p_3620187407_sub_436351764_3965413181(char *, char *, char *, char *, int );


static void work_a_4006990688_0521025630_p_0(char *t0)
{
    char t18[16];
    char t21[16];
    char *t1;
    unsigned char t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    unsigned int t13;
    int t14;
    unsigned int t15;
    unsigned int t16;
    unsigned int t17;
    int t19;
    unsigned int t20;
    unsigned int t22;
    unsigned char t23;
    char *t24;
    char *t25;
    char *t26;
    char *t27;

LAB0:    xsi_set_current_line(32, ng0);
    t1 = (t0 + 992U);
    t2 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t2 != 0)
        goto LAB2;

LAB4:
LAB3:    t1 = (t0 + 3272);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(33, ng0);
    t3 = (t0 + 1192U);
    t4 = *((char **)t3);
    t3 = (t0 + 3352);
    t5 = (t3 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t4, 4U);
    xsi_driver_first_trans_fast(t3);
    xsi_set_current_line(34, ng0);
    t1 = (t0 + 1512U);
    t3 = *((char **)t1);
    t1 = (t0 + 5260U);
    t4 = (t0 + 1192U);
    t5 = *((char **)t4);
    t4 = (t0 + 5228U);
    t2 = ieee_p_3620187407_sub_4042748798_3965413181(IEEE_P_3620187407, t3, t1, t5, t4);
    if (t2 != 0)
        goto LAB5;

LAB7:    t1 = (t0 + 1672U);
    t3 = *((char **)t1);
    t1 = (t0 + 5276U);
    t4 = xsi_get_transient_memory(2U);
    memset(t4, 0, 2U);
    t5 = t4;
    if (-1 == -1)
        goto LAB10;

LAB11:    t13 = 0;

LAB12:    t14 = (t13 - 1);
    t15 = (t14 * -1);
    t16 = (1U * t15);
    t6 = (t5 + t16);
    t17 = (1U * 2U);
    memset(t6, (unsigned char)2, t17);
    t7 = (t18 + 0U);
    t8 = (t7 + 0U);
    *((int *)t8) = 0;
    t8 = (t7 + 4U);
    *((int *)t8) = 1;
    t8 = (t7 + 8U);
    *((int *)t8) = 1;
    t19 = (1 - 0);
    t20 = (t19 * 1);
    t20 = (t20 + 1);
    t8 = (t7 + 12U);
    *((unsigned int *)t8) = t20;
    t2 = ieee_p_3620187407_sub_4042748798_3965413181(IEEE_P_3620187407, t3, t1, t4, t18);
    if (t2 != 0)
        goto LAB8;

LAB9:    xsi_set_current_line(36, ng0);
    t1 = (t0 + 1192U);
    t3 = *((char **)t1);
    t1 = (t0 + 3480);
    t4 = (t1 + 56U);
    t5 = *((char **)t4);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    memcpy(t7, t3, 4U);
    xsi_driver_first_trans_fast_port(t1);

LAB6:    goto LAB3;

LAB5:    xsi_set_current_line(34, ng0);
    t6 = xsi_get_transient_memory(2U);
    memset(t6, 0, 2U);
    t7 = t6;
    memset(t7, (unsigned char)3, 2U);
    t8 = (t0 + 3416);
    t9 = (t8 + 56U);
    t10 = *((char **)t9);
    t11 = (t10 + 56U);
    t12 = *((char **)t11);
    memcpy(t12, t6, 2U);
    xsi_driver_first_trans_fast(t8);
    goto LAB6;

LAB8:    xsi_set_current_line(35, ng0);
    t8 = (t0 + 1672U);
    t9 = *((char **)t8);
    t8 = (t0 + 5276U);
    t10 = ieee_p_3620187407_sub_436351764_3965413181(IEEE_P_3620187407, t21, t9, t8, 1);
    t11 = (t21 + 12U);
    t20 = *((unsigned int *)t11);
    t22 = (1U * t20);
    t23 = (2U != t22);
    if (t23 == 1)
        goto LAB13;

LAB14:    t12 = (t0 + 3416);
    t24 = (t12 + 56U);
    t25 = *((char **)t24);
    t26 = (t25 + 56U);
    t27 = *((char **)t26);
    memcpy(t27, t10, 2U);
    xsi_driver_first_trans_fast(t12);
    goto LAB6;

LAB10:    t13 = 1;
    goto LAB12;

LAB13:    xsi_size_not_matching(2U, t22, 0);
    goto LAB14;

}


extern void work_a_4006990688_0521025630_init()
{
	static char *pe[] = {(void *)work_a_4006990688_0521025630_p_0};
	xsi_register_didat("work_a_4006990688_0521025630", "isim/testTop_isim_beh.exe.sim/work/a_4006990688_0521025630.didat");
	xsi_register_executes(pe);
}
