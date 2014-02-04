------------------------------------------------------------------------------
-- Simple Binary Calculator
--
-- Name: Bingkun Guo
-- Date: Jan/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab1
-- File Name: calculator.vhd
-- 
-- This circuit implements a simple binary calculator with three
-- operations
--
--		clear stored value
--		enter new value
--		add to stored value
--
--	The input data and the result are both 16 bit values.
--
-- Assumptions and Document of my changes:
-- 1. Added input: mode. "mode" indicates the mode of the addition.
-- If mode = 1 then it is signed addition, if mode = 0, it's unsigned.
-- 
-- 2. Added output: error. "error" indicates whether an arithmetic overflow
-- has happended or not(1 for overflow, 0 for no overflow).
--
-- 3. Added internal signals: (1)I have added an internal signal sum, 
-- which instantly reflect the value of dReg + dIn, thus we can easily get 
-- their sum and anaylize in the error detector. (2) I also have added noather 
-- internal signal errorBit, which indicates whether an overflow has happened 
-- or not(1->overflow, 0->no overflow). This errorBit will assign to the 
-- error output.
--
-- 4. I changed the if-statements so that the calculator will ignore any opeartion
-- except "clear" when an error has been detected.
--
-- 5. I check whether an addition will cause overflow by using the most significant
-- bit of sum, dReg and dIn, which are respectively sum(15), dReg(15), dIn(15). 
--
------------------------------------------------------------------------------
library IEEE;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_unsigned.all;
use work.commonDefs.all;

entity calculator is port (
	clk: in std_logic;
	clear, load, add, mode: in std_logic;	-- signals to enable operations
	dIn: in word;		   	-- input data
	result: out word;			-- output result
	error: out std_logic);	-- output error	
end calculator;

architecture a1 of calculator is
signal dReg : word;
signal errorBit : std_logic := '0';	-- added internal signal errorBit
signal sum : word;						-- added internal signal sum

begin

	sum <= dReg + dIn;	-- get the sum of dReg and dIn
	process (clk) 
	begin
		if rising_edge(clk) then
			if clear = '1' then
				dReg <= (wordsize-1 downto 0 => '0');
				errorBit <= '0';
			elsif load = '1' and errorBit = '0' then
				dReg <= dIn;
			elsif add = '1' and errorBit = '0' then
				dReg <= dReg + dIn;
				if mode = '0' then	-- if it is unsigned
					if (dReg(15) or dIn(15)) = '1' and sum(15) = '0' then	
						errorBit <= '1'; -- overflow when carry happens on the sign bit
					end if;
				else -- if it is signed
					if dReg(15) = '1' and dIn(15) = '1' and sum(15) = '0' then
						errorBit <= '1';	-- overflow when two negatives sum to positive
					elsif dReg(15) = '0' and dIn(15) = '0' and sum(15) = '1' then
						errorBit <= '1';	-- overflow when two positives sum to negative
					end if;
				end if;
			end if;
		end if;
	end process;
	
	error <= errorBit;	-- update the error output
	result <= dReg;
end a1;

