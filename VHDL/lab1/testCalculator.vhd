--------------------------------------------------------------------------------
-- Testbench for calculator module
-- Jon Turner, 12/2007
--
-- Modified the_date, your name
--
-- Document your changes here
--
--------------------------------------------------------------------------------

LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.numeric_std.ALL;
USE ieee.std_logic_unsigned.all;
use work.commonDefs.all;

entity testCalculator is
end testCalculator;

architecture a1 of testCalculator is 
component calculator	port(
	clk : in std_logic;
	clear, load, add, mode : in std_logic;
	dIn : in word;          
	result : out word;
	error : out std_logic);
end component;

signal clk :  std_logic := '0';
signal clear :  std_logic := '0';
signal load :  std_logic := '0';
signal add :  std_logic := '0';
signal dIn :  word := (others=>'0');
signal result :  word;
-- add two signals
signal mode: std_logic := '1';
signal error : std_logic := '0';

begin
	-- create instance of calculator circuit
	uut: calculator port map(
		clk => clk, clear => clear, load => load,
		add => add, dIn => dIn, result => result,
		mode => mode, error => error
	);
	
	process begin  -- clock process for clk
		clk_loop : loop
			clk <= '0'; wait for 10 ns;
         clk <= '1'; wait for 10 ns;
      end loop clk_loop;
   end process;

	tb : process begin -- test inputs		
		clear <= '1'; load <= '1'; add <= '1'; dIn <= x"ffff"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"8000"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"8000"; wait for 20 ns;
		clear <= '0'; load <= '1'; add <= '0'; dIn <= x"0002"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"ffff"; wait for 20 ns;
		clear <= '0'; load <= '1'; add <= '0'; dIn <= x"ffff"; wait for 20 ns;
		clear <= '0'; load <= '1'; add <= '1'; dIn <= x"ffff"; wait for 20 ns;
		clear <= '1'; load <= '0'; add <= '1'; dIn <= x"0001"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0002"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0003"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0100"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0200"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"f300"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0400"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0500"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0600"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0700"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"0900"; wait for 20 ns;
		clear <= '0'; load <= '0'; add <= '1'; dIn <= x"f900"; wait for 20 ns;
		wait for 20 ns;
		
		assert (false) report "Simulation ended normally." severity failure;
	end process;
end a1;
