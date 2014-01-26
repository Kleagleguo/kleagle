------------------------------------------------------------------------------
-- Simple Binary Calculator
-- Jon Turner, 12/2007
-- modified 5/2010 for newer prototype boards
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
-- Modified the_date, your name
--
-- Document your changes here
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
	result: out word;			-- output resul
	error: out std_logic);	-- output error	
end calculator;

architecture a1 of calculator is
signal dReg : word;
signal errorBit : std_logic := '0';
signal x : word;

begin
	process (clk) 
	variable oldReg : word;
	variable sum : word;
	begin
		if rising_edge(clk) then
			if clear = '1' then
				dReg <= (wordsize-1 downto 0 => '0');
				errorBit <= '0';
			elsif load = '1' and errorBit = '0' then
				dReg <= dIn;
			elsif add = '1' and errorBit = '0' then
				oldReg := dReg;
				sum := dReg + dIn;
				dReg <= dReg + dIn;
				x <= dReg; 
				if mode = '0' then
					if oldReg(15) = '1' and dIn(15) = '1' then
						errorBit <= '1';
					elsif (oldReg(15) or dIn(15)) = '1' and sum(15) = '0' then
						errorBit <= '1';
					end if;
				else 
					if oldReg(15) = '1' and dIn(15) = '1' and sum(15) = '0' then
						errorBit <= '1';
					elsif oldReg(15) = '0' and dIn(15) = '0' and sum(15) = '1' then
						errorBit <= '1';
					end if;
				end if;
			end if;
		end if;
	end process;
	error <= errorBit;
	result <= dReg;
end a1;

