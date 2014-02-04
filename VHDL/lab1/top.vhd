---------------------------------------------------------------------
-- Top module for simple calculator on S3 board
-- 
-- Name: Bingkun Guo
-- Date: Jan/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab1
-- File Name: top.vhd
--
-- This version uses just the buttons and switches plus the LEDs.
-- 
-- Document of my changes:
-- I simply added signal input and output for component calculator and
-- binaryOutMod so that they are consistent with their entity declaration
-- in calculator.vhd and binaryOutMod.vhd.
--
-- I also set the mode to swt(0), led(1) to error, led(0) to mode.
--
---------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use work.commonDefs.all;

entity top is port(
	clk: in std_logic;
	-- S3 board buttons, knob, switches and LEDs
	btn: in buttons;
	knob: in knobSigs;
	swt: in switches;
   led: out leds;
	-- signals for controlling LCD display 
	lcd: out lcdSigs);
end top;

architecture a1 of top is

component calculator port (
	clk: in std_logic;
	clear, load, add, mode : in std_logic;
	din : in word;
	result: out word;
	error : out std_logic);
end component;

component binaryInMod port(
	clk: in std_logic;
	btn: in buttons;
	knob: in knobSigs;
	resetOut: out std_logic;
	dBtn: out std_logic_vector(3 downto 1);
	pulse: out std_logic_vector(3 downto 1);
	inBits: out word);
end component;

component binaryOutMod port(
	clk, reset, error : in std_logic;
	topRow, botRow: in word;
	lcd: out lcdSigs);
end component binaryOutMod;

signal reset, clear, load, add : std_logic;
signal dBtn, pulse: std_logic_vector(3 downto 1);
signal inBits, outBits: word;
signal mode : std_logic;	-- added internal signal mode
signal error : std_logic;	-- added internal signal error

begin
	
   -- connect the sub-components	
	imod: binaryInMod port map(clk,btn,knob,reset,dBtn,pulse,inBits);
	calc: calculator port map(clk,clear,load,add,mode,inBits,outBits,error);
	omod: binaryOutMod port map(clk,reset,error,inBits,outBits,lcd);
	
	-- define internal control signals
	clear <= dBtn(1) or reset;
	load <= pulse(2);
	add <= pulse(3);
	mode <= swt(0);			-- connect mode to swt(0)
	
	-- connect a few input and output bits to leds
	led(7 downto 4) <= inbits(3 downto 0); 
	led(3 downto 2) <= outBits(3 downto 2);
	led(1) <= error;			-- connect led(1) to error
	led(0) <= mode;			-- connect led(0) to mode
	
end a1;
