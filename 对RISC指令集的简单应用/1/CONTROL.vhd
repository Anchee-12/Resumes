LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY CONTROL IS
    PORT (   CLK :  IN STD_LOGIC;
             RST :  IN STD_LOGIC;
             I : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
             M : IN STD_LOGIC_VECTOR(7 DOWNTO 0);
             Q : OUT STD_LOGIC_VECTOR(3 DOWNTO 0) );
END  CONTROL;

ARCHITECTURE behave OF  CONTROL IS
BEGIN
 PROCESS(I,M,RST,CLK)
 BEGIN
 
	IF RST='1'
	THEN
		Q<="0000";

	ELSE
    IF clk'event and clk='1' 
    THEN
		IF I="0010"
		THEN
           CASE M IS 
             WHEN "00010000"=> Q<="0010";  --IN0 02 
             WHEN "00100100"=> Q<="0011";  --IN1 03
             WHEN "00110100"=> Q<="0100";  --ADD 04
             WHEN "01000100"=> Q<="0111";  --SUB 07
             WHEN "01010000"=> Q<="1010";  --OUT 12
             WHEN "01000000"=> Q<="1111"; --jump 15
             WHEN OTHERS=> Q<="0000";
          END CASE;
        ELSE
          Q<=I;
      END IF;
    END IF;
 END IF;
 END PROCESS;
END behave;