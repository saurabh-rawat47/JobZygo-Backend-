'use client';

import { useState } from 'react';
import { Search, Filter } from 'lucide-react';
import Input from '@/components/ui/Input';
import Button from '@/components/ui/Button';

interface JobSearchProps {
  onSearch: (searchTerm: string) => void;
  onClear: () => void;
  isLoading?: boolean;
}

export default function JobSearch({ onSearch, onClear, isLoading }: JobSearchProps) {
  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();
    if (searchTerm.trim()) {
      onSearch(searchTerm.trim());
    }
  };

  const handleClear = () => {
    setSearchTerm('');
    onClear();
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6 mb-6">
      <form onSubmit={handleSearch} className="flex gap-4">
        <div className="flex-1 relative">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
          <Input
            type="text"
            placeholder="Search jobs by title, company, or technology..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="pl-10"
          />
        </div>
        <Button
          type="submit"
          isLoading={isLoading}
          disabled={!searchTerm.trim()}
        >
          Search
        </Button>
        {searchTerm && (
          <Button
            type="button"
            variant="outline"
            onClick={handleClear}
          >
            Clear
          </Button>
        )}
      </form>
    </div>
  );
}



